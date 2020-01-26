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

    private static final String VALID_LOGIN = "ANY_VALID_LOGIN";
    private static final String VALID_PASSWORD = "ANY_VALID_PASSWORD";
    private static final String VALID_FIRST_NAME = "ANY_VALID_FIRST_NAME";
    private static final String VALID_LAST_NAME = "ANY_VALID_LAST_NAME";

    private ValidationHelper validationHelper = new ValidationHelper();

    public Object[] invalidValues() {
        return new Object[]{null, "", "1", "22", "333", "4444"};
    }

    @Test
    @Parameters(method = "invalidValues")
    public void should_have_violation_when_login_has_invalid_value(String invalidLogin) {
        ApplicationUser user = ApplicationUser.builder()
                .login(invalidLogin)
                .password(VALID_PASSWORD)
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
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
                .login(VALID_LOGIN)
                .password(invalidPassword)
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasMoreViolationsThan(1)
                .hasWrongValueAtIndex(0, invalidPassword);
    }

    @Test
    @Parameters(method = "invalidValues")
    public void should_have_violation_when_first_name_has_invalid_value(String invalidFirstName) {
        ApplicationUser user = ApplicationUser.builder()
                .login(VALID_LOGIN)
                .password(VALID_PASSWORD)
                .firstName(invalidFirstName)
                .lastName(VALID_LAST_NAME)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasMoreViolationsThan(1)
                .hasWrongValueAtIndex(0, invalidFirstName);
    }

    @Test
    @Parameters(method = "invalidValues")
    public void should_have_violation_when_last_name_has_invalid_value(String invalidLastName) {
        ApplicationUser user = ApplicationUser.builder()
                .login(VALID_LOGIN)
                .password(VALID_PASSWORD)
                .firstName(VALID_FIRST_NAME)
                .lastName(invalidLastName)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasMoreViolationsThan(1)
                .hasWrongValueAtIndex(0, invalidLastName);
    }

    @Test
    public void should_not_have_any_violations_when_user_is_valid() {
        ApplicationUser user = ApplicationUser.builder()
                .login(VALID_LOGIN)
                .password(VALID_PASSWORD)
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
                .build();

        Set<ConstraintViolation<ApplicationUser>> violations = validationHelper.validate(user);

        ConstraintViolationAssert.assertThat(violations)
                .hasViolationSize(0);
    }


}