package dev.akozel.cleaningtime.repository.postgres.user.converter;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.repository.postgres.tables.records.UsersRecord;

/**
 * UserConverter. Converts from core model to persistent
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
public interface UserConverter {

    UsersRecord convert(ApplicationUser user);

    ApplicationUser unconvert(UsersRecord usersRecord);
}
