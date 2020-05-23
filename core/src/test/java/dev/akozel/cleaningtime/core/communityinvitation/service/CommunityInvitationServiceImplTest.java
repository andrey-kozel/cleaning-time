package dev.akozel.cleaningtime.core.communityinvitation.service;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.core.communityinvitation.repository.CommunityInvitationRepository;
import dev.akozel.cleaningtime.core.communityinvitation.validation.CommunityInvitationValidator;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;
import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

@RunWith(MockitoJUnitRunner.class)
public class CommunityInvitationServiceImplTest {

    private static final CommunityInvitationStatus ANY_INVITATION_STATUS = CommunityInvitationStatus.REJECTED;
    private static final Long EXISTING_INVITATION_ID = 1123L;
    private static final Long EXISTING_COMMUNITY_ID = 13213L;
    private static final Long EXISTING_USER_ID = 12432L;
    private static final String EXISTING_USER_EMAIL = "existing@user.email";
    private static final ApplicationUser EXISTING_USER = ApplicationUser.builder()
            .id(EXISTING_USER_ID)
            .email(EXISTING_USER_EMAIL)
            .build();
    private static final CommunityInvitation EXISTING_INVITATION = CommunityInvitation.builder()
            .id(EXISTING_INVITATION_ID)
            .userId(EXISTING_USER_ID)
            .communityId(EXISTING_COMMUNITY_ID)
            .status(CommunityInvitationStatus.REJECTED)
            .build();

    private static final String UNKNOWN_USER_EMAIL = "unknown@user.email";
    private static final Long MISSING_COMMUNITY_ID = 13213L;

    private static final ApplicationValidationException VALIDATION_EXCEPTION = new ApplicationValidationException(null);

    @Mock
    private CommunityInvitationRepository invitationsRepository;
    @Mock
    private ApplicationUserService userService;
    @Mock
    private CommunityInvitationValidator invitationValidator;
    @InjectMocks
    private CommunityInvitationServiceImpl sut;

    @Test
    public void should_submit_invitation_for_user_when_user_exists() {
        //given
        given(userService.getByEmail(EXISTING_USER_EMAIL))
                .willReturn(EXISTING_USER);

        //when
        sut.inviteUser(EXISTING_COMMUNITY_ID, EXISTING_USER_EMAIL);

        //then
        then(invitationsRepository)
                .should()
                .save(CommunityInvitation.builder()
                        .userId(EXISTING_USER_ID)
                        .communityId(EXISTING_COMMUNITY_ID)
                        .status(CommunityInvitationStatus.SUBMITTED)
                        .build());
    }

    @Test
    public void should_throw_an_exception_when_incoming_arameters_are_not_valid() {
        //given
        willThrow(VALIDATION_EXCEPTION)
                .given(invitationValidator)
                .validateSave(MISSING_COMMUNITY_ID, UNKNOWN_USER_EMAIL);

        //when
        when(() -> sut.inviteUser(MISSING_COMMUNITY_ID, UNKNOWN_USER_EMAIL));

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class);
    }

    @Test
    public void should_update_with_resubmitted_status_previous_invitation_when_it_is_already_exists() {
        //given
        given(userService.getByEmail(EXISTING_USER_EMAIL))
                .willReturn(EXISTING_USER);
        given(invitationsRepository.findByCommunityAndUser(EXISTING_COMMUNITY_ID, EXISTING_USER_ID))
                .willReturn(EXISTING_INVITATION);

        //when
        sut.inviteUser(EXISTING_COMMUNITY_ID, EXISTING_USER_EMAIL);

        //then
        then(invitationsRepository)
                .should()
                .update(EXISTING_INVITATION_ID, CommunityInvitationStatus.RESUBMITTED);
    }

    @Test
    public void should_update_invitation() {
        //when
        Long actual = sut.respondForInvitation(EXISTING_INVITATION_ID, ANY_INVITATION_STATUS);

        //then
        then(invitationsRepository)
                .should()
                .update(EXISTING_INVITATION_ID, ANY_INVITATION_STATUS);
        assertThat(actual)
                .isEqualTo(EXISTING_INVITATION_ID);
    }

}