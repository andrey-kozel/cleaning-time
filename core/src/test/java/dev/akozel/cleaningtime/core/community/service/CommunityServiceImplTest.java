package dev.akozel.cleaningtime.core.community.service;

import com.googlecode.catchexception.apis.BDDCatchException;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.validation.CommunityValidator;
import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

@RunWith(JUnitParamsRunner.class)
public class CommunityServiceImplTest {

    private static final Community NULL_COMMUNITY = null;
    private static final Community VALID_COMMUNITY = Community.builder()
            .name("SOME_VALID_COMMUNITY_NAME")
            .build();
    private static final Long ANY_VALID_COMMUNITY_ID = 67543L;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private CommunityRepository communityRepository;
    @Mock
    private CommunityValidator communityValidator;
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
    public void should_not_save_community_when_community_is_invalid() {
        //given
        Community invalidCommunity = Community.builder()
                .name(null)
                .build();
        willThrow(ApplicationValidationException.class)
                .given(communityValidator)
                .validateCreate(isA(Community.class));

        //when
        BDDCatchException.when(sut)
                .create(invalidCommunity);

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

}