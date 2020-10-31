package dev.akozel.cleaningtime.rest.auth.validation;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;

/**
 * AuthValidator. Validates parameters for user registration
 * <p>
 * Date: 20/03/2020
 *
 * @author Andrey Kozel
 */
public interface AuthValidator {

    void validateRegistration(ApplicationUser user, String passwordConfirmation);

}
