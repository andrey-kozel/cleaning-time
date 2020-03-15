package dev.akozel.cleaningtime.core.user.validation;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;

/**
 * UserValidator. validation for request params of the user service
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
public interface UserValidator {

    void validateSave(ApplicationUser user);

    void validateGetByEmail(String email);

}
