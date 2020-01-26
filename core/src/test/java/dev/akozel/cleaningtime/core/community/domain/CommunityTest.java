package dev.akozel.cleaningtime.core.community.domain;

import dev.akozel.cleaningtime.core.helper.ValidationHelper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static dev.akozel.cleaningtime.core.helper.ConstraintViolationAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class CommunityTest {

    private static final String EMPTY_COMMUNITY_NAME = "";
    private ValidationHelper validationHelper = new ValidationHelper();

    public Object[] invalidCommunityNames() {
        return new Object[]{null, "1", "22"};
    }

    @Test
    @Parameters(method = "invalidCommunityNames")
    public void should_return_violations_when_parameter_invalid(String invalidName) {
        Community invalidCommunity = Community.builder()
                .name(invalidName)
                .build();
        //when
        Set<ConstraintViolation<Community>> violations = validationHelper.validate(invalidCommunity);

        //then
        assertThat(violations)
                .hasViolationSize(1)
                .hasWrongValueAtIndex(0, invalidName);
    }

    public Object[] validCommunityNames() {
        return new Object[]{"short", "longlonglonglonglonglonglonglonglonglonglong name", "1!@#$&^%$&*%$^%@"};
    }

    @Test
    @Parameters(method = "validCommunityNames")
    public void should_not_return_violations_for_valid_values(String validName) {
        Community invalidCommunity = Community.builder()
                .name(validName)
                .build();

        //when
        Set<ConstraintViolation<Community>> violations = validationHelper.validate(invalidCommunity);

        //then
        assertThat(violations)
                .hasViolationSize(0);
    }

    @Test
    public void should_have_two_violations_when_name_is_empty() {
        Community invalidCommunity = Community.builder()
                .name(EMPTY_COMMUNITY_NAME)
                .build();
        //when
        Set<ConstraintViolation<Community>> violations = validationHelper.validate(invalidCommunity);

        //then
        assertThat(violations)
                .hasViolationSize(2)
                .hasWrongValueAtIndex(0, EMPTY_COMMUNITY_NAME)
                .hasWrongValueAtIndex(1, EMPTY_COMMUNITY_NAME);
    }

}