package dev.akozel.cleaningtime.rest.auth.validation;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.common.validation.RulesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * AuthValidatorImpl. Validates parameters for user registration
 * <p>
 * Date: 20/03/2020
 *
 * @author Andrey Kozel
 */
@Component
public class AuthValidatorImpl implements AuthValidator {

    private RulesValidator rulesValidator;

    @Autowired
    public AuthValidatorImpl(RulesValidator rulesValidator) {
        this.rulesValidator = rulesValidator;
    }

    @Override
    public void validateRegistration(ApplicationUser user, String passwordConfirmation) {
        if (!Objects.equals(user.getPassword(), passwordConfirmation)) {
            rulesValidator.raiseError("Passwords are not match", "password", user.getPassword());
        }
    }
}
