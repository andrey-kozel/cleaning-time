package dev.akozel.cleaningtime.repository.postgres.user;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.repository.ApplicationUserRepository;
import dev.akozel.cleaningtime.repository.postgres.user.converter.UserConverter;
import org.jooq.DSLContext;
import org.jooq.codegen.maven.example.tables.Users;
import org.jooq.codegen.maven.example.tables.records.UsersRecord;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * PostgresUserRepository. Saves users to postgres
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class PostgresUserRepository implements ApplicationUserRepository {

    private DSLContext context;
    private UserConverter converter;

    @Inject
    public PostgresUserRepository(DSLContext context,
                                  UserConverter converter) {
        this.context = context;
        this.converter = converter;
    }

    @Override
    public Long save(ApplicationUser user) {
        UsersRecord record = converter.convert(user);
        UsersRecord savedRecord = context.insertInto(Users.USERS)
                .set(record)
                .returning(Users.USERS.ID)
                .fetchOne();
        return savedRecord.getId();
    }

    @Override
    public ApplicationUser getByEmail(String email) {
        UsersRecord usersRecord = context.selectFrom(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email))
                .fetchOne();
        return converter.unconvert(usersRecord);
    }
}