package dev.akozel.cleaningtime.core.communityinvitation.validation;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.service.CommunityService;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.core.communityinvitation.repository.CommunityInvitationRepository;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(JUnitParamsRunner.class)
public class CommunityInvitationValidatorImplTest {

    private static final String ANY_USER_EMAIL = "any@email.com";
    private static final CommunityInvitationStatus ANY_INVITATION_STATUS = CommunityInvitationStatus.CONFIRMED;

    private static final Long EXISTING_COMMUNITY_ID = 557L;
    private static final Community EXISTING_COMMUNITY = Community.builder().build();
    private static final String EXISTING_USER_EMAIL = "existing@email.com";
    private static final ApplicationUser EXISTING_USER = ApplicationUser.builder().build();
    private static final Long EXISTING_INVITATION_ID = 557L;
    private static final CommunityInvitation EXISTING_INVITATION = CommunityInvitation.builder().build();

    private static final Long NULL_COMMUNITY_ID = null;
    private static final String MISSING_EMAIL = "missing@email.com";
    private static final String NULL_USER_EMAIL = null;
    private static final Long MISSING_COMMUNITY_ID = 556L;
    private static final Community NULL_COMMUNITY = null;
    private static final Long NULL_INVITATION_ID = null;
    private static final Long MISSING_INVITATION_ID = 556L;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private CommunityInvitationRepository invitationRepository;
    @Mock
    private ApplicationUserService userService;
    @Mock
    private CommunityService communityService;
    @Mock
    private RulesValidator rulesValidator;

    @InjectMocks
    private CommunityInvitationValidatorImpl sut;

    @Test
    public void should_throw_exception_when_save_and_community_id_is_null() {
        //when
        when(() -> sut.validateSave(NULL_COMMUNITY_ID, ANY_USER_EMAIL));

        //then
        then(rulesValidator)
                .should()
                .raiseError("Community id should be present", "communityId", null);

    }

    @Test
    public void should_throw_exception_when_save_and_community_is_not_found() {
        //given
        given(communityService.get(MISSING_COMMUNITY_ID))
                .willReturn(NULL_COMMUNITY);

        //when
        when(() -> sut.validateSave(MISSING_COMMUNITY_ID, ANY_USER_EMAIL));

        //then
        then(rulesValidator)
                .should()
                .raiseError("Community should be present", "community", null);

    }

    @Test
    public void should_throw_exception_when_save_and_user_email_is_null() {
        //given
        given(communityService.get(EXISTING_COMMUNITY_ID))
                .willReturn(EXISTING_COMMUNITY);

        //when
        when(() -> sut.validateSave(EXISTING_COMMUNITY_ID, NULL_USER_EMAIL));

        //then
        then(rulesValidator)
                .should()
                .raiseError("User email should be present", "userEmail", null);
    }

    @Test
    public void should_throw_exception_when_save_and_user_is_not_found() {
        //given
        given(communityService.get(EXISTING_COMMUNITY_ID))
                .willReturn(EXISTING_COMMUNITY);

        //when
        when(() -> sut.validateSave(EXISTING_COMMUNITY_ID, MISSING_EMAIL));

        //then
        then(rulesValidator)
                .should()
                .raiseError("User should be present", "user", null);
    }

    @Test
    public void should_not_throw_exception_when_save_and_user_and_community_are_found() {
        //given
        given(communityService.get(EXISTING_COMMUNITY_ID))
                .willReturn(EXISTING_COMMUNITY);
        given(userService.getByEmail(EXISTING_USER_EMAIL))
                .willReturn(EXISTING_USER);

        //when
        when(() -> sut.validateSave(EXISTING_COMMUNITY_ID, EXISTING_USER_EMAIL));

        //then
        then(rulesValidator)
                .shouldHaveNoInteractions();
    }

    @Test
    public void should_throw_exception_when_respond_and_invitation_id_is_null() {
        //when
        when(() -> sut.validateRespond(NULL_INVITATION_ID, ANY_INVITATION_STATUS));

        //then
        then(rulesValidator)
                .should()
                .raiseError("Invitation id should be present", "invitationId", null);
    }

    @Test
    public void should_throw_exception_when_respond_and_invitation_is_not_found() {
        //when
        when(() -> sut.validateRespond(MISSING_INVITATION_ID, ANY_INVITATION_STATUS));

        //then
        then(rulesValidator)
                .should()
                .raiseError("Invitation should be present", "invitation", null);
    }

    public Object[] invalidStatuses() {
        return new Object[]{
                null,
                CommunityInvitationStatus.SUBMITTED
        };
    }

    @Test
    @Parameters(method = "invalidStatuses")
    public void should_throw_exception_when_respond_and_status_is_invalid(CommunityInvitationStatus invalidStatus) {
        //given
        given(invitationRepository.get(EXISTING_INVITATION_ID))
                .willReturn(EXISTING_INVITATION);

        //when
        when(() -> sut.validateRespond(EXISTING_INVITATION_ID, invalidStatus));

        //then
        then(rulesValidator)
                .should()
                .raiseError("Status should be CONFIRMED, REJECTED, or RESUBMITTED", "status", null);
    }

    public Object[] validStatuses() {
        return new Object[]{
                CommunityInvitationStatus.CONFIRMED,
                CommunityInvitationStatus.REJECTED,
                CommunityInvitationStatus.RESUBMITTED
        };
    }

    @Test
    @Parameters(method = "validStatuses")
    public void should_not_throw_exception_when_respond_and_status_is_valid(CommunityInvitationStatus validStatus) {
        //given
        given(invitationRepository.get(EXISTING_INVITATION_ID))
                .willReturn(EXISTING_INVITATION);

        //when
        when(() -> sut.validateRespond(EXISTING_INVITATION_ID, validStatus));

        //then
        then(rulesValidator)
                .shouldHaveNoInteractions();
    }


}