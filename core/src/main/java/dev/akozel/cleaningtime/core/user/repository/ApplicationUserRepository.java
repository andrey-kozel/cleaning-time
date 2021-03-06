package dev.akozel.cleaningtime.core.user.repository;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;

/**
 * ApplicationUserRepository.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
public interface ApplicationUserRepository {

    Long save(ApplicationUser user);

    ApplicationUser getByEmail(String email);
}
