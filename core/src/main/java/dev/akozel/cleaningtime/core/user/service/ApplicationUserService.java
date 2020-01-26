package dev.akozel.cleaningtime.core.user.service;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;

/**
 * ApplicationUserService.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
public interface ApplicationUserService {

    Integer createUser(ApplicationUser user);

}
