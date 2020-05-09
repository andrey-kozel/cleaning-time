package dev.akozel.cleaningtime.repository.postgres.user;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.repository.postgres.test.providers.DslContextProvider;
import dev.akozel.cleaningtime.repository.postgres.test.rules.PostgresContainerExecutorRule;
import dev.akozel.cleaningtime.repository.postgres.test.annotations.SqlData;
import dev.akozel.cleaningtime.repository.postgres.user.converter.UserRecordConverterImpl;
import org.jooq.exception.DataAccessException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

public class PostgresUserRepositoryIT {

    private static final String MISSING_EMAIL = "email@email.email";
    private static final String EXISTING_EMAIL = "existing_email@email.email";
    private static final String VALID_EMAIL = "valid_email@email.email";
    private static final String VALID_PASSWORD = "valid_password";

    @Rule
    public final PostgresContainerExecutorRule postgresContainerExecutorRule = new PostgresContainerExecutorRule();

    private PostgresUserRepository sut;

    @Before
    public void init() {
        sut = new PostgresUserRepository(DslContextProvider.getContext(), new UserRecordConverterImpl());
    }

    @Test
    public void should_return_null_when_non_existing_email_provided() {
        //when
        ApplicationUser actual = sut.getByEmail(MISSING_EMAIL);

        //then
        assertNull(actual);
    }

    @Test
    @SqlData(initialScript = "users/insert_user.sql")
    public void should_return_user_when_provided_email_exists() {

        //when
        ApplicationUser actual = sut.getByEmail(EXISTING_EMAIL);

        assertThat(actual)
                .hasFieldOrPropertyWithValue("email", EXISTING_EMAIL);
    }

    @Test
    public void should_return_id_when_user_was_saved() {
        //given
        ApplicationUser user = ApplicationUser.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .build();

        //when
        Long actual = sut.save(user);

        //then
        assertThat(actual)
                .isNotNull()
                .isPositive();
    }

    @Test
    public void should_throw_exception_when_save_user_with_null_password() {
        //given
        ApplicationUser user = ApplicationUser.builder()
                .email(VALID_EMAIL)
                .build();

        //when
        when(() -> sut.save(user));

        //then
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("null value in column \"password\" violates not-null constraint");
    }

    @Test
    public void should_throw_exception_when_save_user_with_null_email() {
        //given
        ApplicationUser user = ApplicationUser.builder()
                .password(VALID_PASSWORD)
                .build();

        //when
        when(() -> sut.save(user));

        //then
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("null value in column \"email\" violates not-null constraint");
    }

    @Test
    public void should_throw_exception_when_save_user_with_existing_email() {
        //given
        ApplicationUser user = ApplicationUser.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .build();

        //when
        sut.save(user);
        when(() -> sut.save(user));

        //then
        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("duplicate key value violates unique constraint \"users_email_key\"");
    }

}