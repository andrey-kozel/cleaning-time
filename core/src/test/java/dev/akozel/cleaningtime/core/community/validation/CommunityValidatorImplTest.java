package dev.akozel.cleaningtime.core.community.validation;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class CommunityValidatorImplTest {

    private static final Long NULL_COMMUNITY_ID = null;
    private static final Community VALID_COMMUNITY = Community
            .builder()
            .build();

    @Mock
    private RulesValidator rulesValidator;
    @InjectMocks
    private CommunityValidatorImpl sut;

    @Test
    public void should_raise_error_when_community_id_is_null_when_get_called() {
        //when
        sut.validateGet(NULL_COMMUNITY_ID);

        //then
        then(rulesValidator)
                .should()
                .raiseError(isA(String.class), isA(String.class), isNull());
    }

    @Test
    public void should_raise_error_when_community_id_is_null_when_update_called() {
        //when
        sut.validateUpdate(NULL_COMMUNITY_ID, VALID_COMMUNITY);

        //then
        then(rulesValidator)
                .should()
                .raiseError(isA(String.class), isA(String.class), isNull());
    }


}
