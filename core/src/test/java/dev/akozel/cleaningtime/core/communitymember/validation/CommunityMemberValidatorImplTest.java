package dev.akozel.cleaningtime.core.communitymember.validation;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.common.validation.RulesValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CommunityMemberValidatorImplTest {

    private static final CommunityMember ANY_COMMUNITY_MEMBER = CommunityMember
            .builder()
            .build();

    @Mock
    private RulesValidator rulesValidator;
    @InjectMocks
    private CommunityMemberValidatorImpl communityMemberValidator;

    @Test
    public void should_call_rules_validator() {
        //when
        communityMemberValidator.validateCreate(ANY_COMMUNITY_MEMBER);

        //then
        then(rulesValidator)
                .should()
                .validate(ANY_COMMUNITY_MEMBER);
    }

}