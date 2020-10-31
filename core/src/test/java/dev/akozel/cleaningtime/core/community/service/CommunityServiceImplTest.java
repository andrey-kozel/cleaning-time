package dev.akozel.cleaningtime.core.community.service;

import com.googlecode.catchexception.apis.BDDCatchException;
import dev.akozel.cleaningtime.core.common.context.UserContext;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.validation.CommunityValidator;
import dev.akozel.cleaningtime.core.communitymember.service.CommunityMemberService;
import dev.akozel.cleaningtime.core.common.validation.exception.ApplicationValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

@RunWith(MockitoJUnitRunner.class)
public class CommunityServiceImplTest {

    private static final Community VALID_COMMUNITY = Community.builder()
            .name("SOME_VALID_COMMUNITY_NAME")
            .build();
    private static final Long ANY_VALID_COMMUNITY_ID = 67543L;
    private static final Long ANY_VALID_USER_ID = 46454L;

    @Mock
    private CommunityRepository communityRepository;
    @Mock
    private CommunityValidator communityValidator;
    @Mock
    private CommunityMemberService communityMemberService;
    @Mock
    private UserContext userContext;
    @InjectMocks
    private CommunityServiceImpl sut;

    @Test
    public void should_save_community_with_given_name() {
        //when
        sut.create(VALID_COMMUNITY);

        //then
        then(communityRepository)
                .should()
                .save(VALID_COMMUNITY);
    }

    @Test
    public void should_return_id_of_created_community_when_name_exists() {
        //given
        given(communityRepository.save(isA(Community.class)))
                .willReturn(ANY_VALID_COMMUNITY_ID);

        //when
        Long actual = sut.create(VALID_COMMUNITY);

        //then
        assertThat(actual)
                .as("Returned ID is not equal to the desired")
                .isEqualTo(ANY_VALID_COMMUNITY_ID);
    }

    @Test
    public void should_create_owner_after_community_created() {
        //given
        given(communityRepository.save(isA(Community.class)))
                .willReturn(ANY_VALID_COMMUNITY_ID);

        //when
        Long actual = sut.create(VALID_COMMUNITY);

        //then
        then(communityMemberService)
                .should()
                .createOwner(actual);
    }

    @Test
    public void should_not_save_community_when_community_is_invalid() {
        //given
        Community invalidCommunity = Community.builder()
                .name(null)
                .build();
        willThrow(ApplicationValidationException.class)
                .given(communityValidator)
                .validateCreate(isA(Community.class));

        //when
        when(() -> sut.create(invalidCommunity));

        //then
        assertThat(BDDCatchException.caughtException())
                .isInstanceOf(ApplicationValidationException.class);
        then(communityRepository)
                .shouldHaveNoInteractions();
    }

    @Test
    public void should_return_community_if_it_exists() {
        //given
        Community anyCommunity = Community.builder()
                .build();
        given(communityRepository.get(ANY_VALID_COMMUNITY_ID))
                .willReturn(anyCommunity);

        //when
        Community actual = sut.get(ANY_VALID_COMMUNITY_ID);

        //then
        assertThat(actual)
                .isNotNull()
                .isSameAs(anyCommunity);
    }

    @Test
    public void should_validate_community_before_update() {
        //given
        Community anyCommunity = Community.builder()
                .build();
        Integer anyIdentifier = 127;

        //when
        sut.update(ANY_VALID_COMMUNITY_ID, anyCommunity);

        //then
        then(communityValidator)
                .should()
                .validateUpdate(ANY_VALID_COMMUNITY_ID, anyCommunity);
    }

    @Test
    public void should_update_community_if_request_valid() {
        //given
        Community anyCommunity = Community.builder()
                .build();

        //when
        sut.update(ANY_VALID_COMMUNITY_ID, anyCommunity);

        //then
        then(communityRepository)
                .should()
                .update(ANY_VALID_COMMUNITY_ID, anyCommunity);
    }

    @Test
    public void should_find_communities_by_user_in_the_context() {
        //given
        given(userContext.getUserId())
                .willReturn(ANY_VALID_USER_ID);

        //when
        sut.findByUser();

        //then
        then(communityRepository)
                .should()
                .find(ANY_VALID_USER_ID);
    }

    @Test
    public void should_validate_id_before_delete() {
        //when
        sut.delete(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityValidator)
                .should()
                .validateDelete(ANY_VALID_COMMUNITY_ID);
    }

    @Test
    public void should_delete_community_members() {
        //when
        sut.delete(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityMemberService)
                .should()
                .deleteByCommunityId(ANY_VALID_COMMUNITY_ID);
    }

    @Test
    public void should_delete_community_by_id() {
        //when
        sut.delete(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityRepository)
                .should()
                .delete(ANY_VALID_COMMUNITY_ID);
    }
}