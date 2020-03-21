package dev.akozel.cleaningtime.repository.postgres.user.converter;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.repository.postgres.tables.records.UsersRecord;

import javax.inject.Named;

/**
 * UserConverterImpl.
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class UserConverterImpl implements UserConverter {

    @Override
    public UsersRecord convert(ApplicationUser user) {
        UsersRecord target = new UsersRecord();
        target.setEmail(user.getEmail());
        target.setPassword(user.getPassword());
        return target;
    }

    @Override
    public ApplicationUser unconvert(UsersRecord usersRecord) {
        if (usersRecord == null) {
            return null;
        }
        return ApplicationUser.builder()
                .email(usersRecord.getEmail())
                .password(usersRecord.getPassword())
                .id(usersRecord.getId())
                .build();
    }
}
