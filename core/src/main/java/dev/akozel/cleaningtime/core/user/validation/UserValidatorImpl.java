package dev.akozel.cleaningtime.core.user.validation;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

/**
 * UserValidatorImpl.
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class UserValidatorImpl implements UserValidator {

    private RulesValidator rulesValidator;

    @Inject
    public UserValidatorImpl(RulesValidator rulesValidator) {
        this.rulesValidator = rulesValidator;
    }

    @Override
    public void validateRegisterUser(ApplicationUser user, String passwordConfirmation) {
        rulesValidator.validate(user);
        if (!Objects.equals(user.getPassword(), passwordConfirmation)) {
            rulesValidator.raiseError("Passwords are not match", "password", user.getPassword());
        }
    }

    @Override
    public void validateGetByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            rulesValidator.raiseError("Email must not be empty", "email", email);
        }
    }
}
