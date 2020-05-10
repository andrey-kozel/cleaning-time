package dev.akozel.cleaningtime.core.communitymember.domain;

import dev.akozel.cleaningtime.core.helper.ValidationHelper;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static dev.akozel.cleaningtime.core.helper.ConstraintViolationAssert.assertThat;

public class CommunityMemberTest {

    private static final String NULL_VALUE = null;
    private static final Integer FIRST_ITEM = 0;
    private static final Long ANY_VALID_ID = 1L;
    private static final CommunityMemberType ANY_VALID_TYPE = CommunityMemberType.OWNER;

    private final ValidationHelper validationHelper = new ValidationHelper();

    @Test
    public void Should_return_violations_when_user_id_is_null() {
        //given
        CommunityMember memberWithNullUser = CommunityMember.builder()
                .communityId(ANY_VALID_ID)
                .type(ANY_VALID_TYPE)
                .build();

        //when
        Set<ConstraintViolation<CommunityMember>> violations = validationHelper.validate(memberWithNullUser);

        assertThat(violations)
                .hasViolationSize(1)
                .hasWrongValueAtIndex(FIRST_ITEM, NULL_VALUE);
    }

    @Test
    public void Should_return_violations_when_community_id_is_null() {
        //given
        CommunityMember memberWithNullCommunity = CommunityMember.builder()
                .userId(ANY_VALID_ID)
                .type(ANY_VALID_TYPE)
                .build();

        //when
        Set<ConstraintViolation<CommunityMember>> violations = validationHelper.validate(memberWithNullCommunity);

        assertThat(violations)
                .hasViolationSize(1)
                .hasWrongValueAtIndex(FIRST_ITEM, NULL_VALUE);
    }

    @Test
    public void Should_return_violations_when_type_is_null() {
        //given
        CommunityMember memberWithNullType = CommunityMember.builder()
                .userId(ANY_VALID_ID)
                .communityId(ANY_VALID_ID)
                .build();

        //when
        Set<ConstraintViolation<CommunityMember>> violations = validationHelper.validate(memberWithNullType);

        assertThat(violations)
                .hasViolationSize(1)
                .hasWrongValueAtIndex(FIRST_ITEM, NULL_VALUE);
    }

    @Test
    public void Should_not_return_violations_when_all_fields_have_values() {
        //given
        CommunityMember memberWithNullType = CommunityMember.builder()
                .userId(ANY_VALID_ID)
                .communityId(ANY_VALID_ID)
                .type(ANY_VALID_TYPE)
                .build();

        //when
        Set<ConstraintViolation<CommunityMember>> violations = validationHelper.validate(memberWithNullType);

        assertThat(violations)
                .hasViolationSize(0);
    }

}