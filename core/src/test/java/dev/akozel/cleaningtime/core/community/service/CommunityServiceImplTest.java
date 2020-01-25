package dev.akozel.cleaningtime.core.community.service;

import com.googlecode.catchexception.apis.BDDCatchException;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    private static final Community VALID_COMMUNITY = Community.builder()
            .name("SOME_VALID_COMMUNITY_NAME")
            .build();
    private static final Integer ANY_SAVED_COMMUNITY_ID = 67543;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private CommunityRepository communityRepository;
    @Mock
    private RulesValidator rulesValidator;
    @Captor
    private ArgumentCaptor<Community> argumentCaptor;
    @InjectMocks
    private CommunityServiceImpl sut;

    @Test
    public void should_save_community_with_given_name() {
        //when
        sut.create(VALID_COMMUNITY);

        //then
        then(communityRepository)
                .should()
                .save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue())
                .isEqualTo(VALID_COMMUNITY);
    }

    @Test
    public void should_return_id_of_created_community_when_name_exists() {
        //given
        given(communityRepository.save(isA(Community.class)))
                .willReturn(ANY_SAVED_COMMUNITY_ID);

        //when
        Integer actual = sut.create(VALID_COMMUNITY);

        //then
        assertThat(actual)
                .as("Returned ID is not equal to the desired")
                .isEqualTo(ANY_SAVED_COMMUNITY_ID);
    }

    @Test
    public void should_throw_an_exception_on_invalid_input() {
        //given
        Community invalidCommunity = Community.builder()
                .build();
        willThrow(ApplicationValidationException.class)
                .given(rulesValidator)
                .validate(isA(Community.class));

        //when
        BDDCatchException.when(sut)
                .create(invalidCommunity);

        //then
        assertThat(BDDCatchException.caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasNoCause();
    }

}