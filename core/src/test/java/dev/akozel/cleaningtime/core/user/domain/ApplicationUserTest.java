package dev.akozel.cleaningtime.core.user.domain;

import dev.akozel.cleaningtime.core.helper.ConstraintViolationAssert;
import dev.akozel.cleaningtime.core.helper.ValidationHelper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ConstraintViolation;
import java.util.Set;

@RunWith(JUnitParamsRunner.class)
public class ApplicationUserTest {

    private static final String VALID_EMAIL = "ANY_VALID_LOGIN";
    private static final String VALID_PASSWORD = "ANY_VALID_PASSWORD";

    private ValidationHelper validationHelper = new ValidationHelper();

    public Object[] invalidValues() {
        return new Object[]{null, "", "1", "22", "333", "4444"};
    }

    @Test
    @Parameters(method = "invalidValues")
    public void should_have_violation_when_login_has_invalid_value(String invalidLogin) {
        ApplicationUser user = ApplicationUser.builder()
                .email(invalidLogin)
                .password(VALID_PASSWORD)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasMoreViolationsThan(1)
                .hasWrongValueAtIndex(0, invalidLogin);
    }

    @Test
    @Parameters(method = "invalidValues")
    public void should_have_violation_when_password_has_invalid_value(String invalidPassword) {
        ApplicationUser user = ApplicationUser.builder()
                .email(VALID_EMAIL)
                .password(invalidPassword)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasMoreViolationsThan(1)
                .hasWrongValueAtIndex(0, invalidPassword);
    }

    @Test
    public void should_not_have_any_violations_when_user_is_valid() {
        ApplicationUser user = ApplicationUser.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasViolationSize(0);
    }


}