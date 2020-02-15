package dev.akozel.cleaningtime.core.user.service;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.repository.ApplicationUserRepository;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationUserServiceImplTest {

    private static final String ANY_VALID_PASSWORD_CONFIRMATION = "password";
    private static final String ANY_VALID_PASSWORD = "password";

    @Mock
    private RulesValidator rulesValidator;
    @Mock
    private ApplicationUserRepository applicationUserRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ApplicationUserServiceImpl sut;

    @Test
    public void should_save_user_when_user_is_valid() {
        //given
        ApplicationUser validUser = ApplicationUser.builder()
                .build();

        //when
        sut.registerUser(validUser, ANY_VALID_PASSWORD_CONFIRMATION);

        //then
        then(applicationUserRepository)
                .should()
                .save(validUser);
    }

    @Test
    public void should_validate_user_before_saving() {
        //given
        ApplicationUser validUser = ApplicationUser.builder()
                .build();

        //when
        sut.registerUser(validUser, ANY_VALID_PASSWORD_CONFIRMATION);

        //then
        then(rulesValidator)
                .should()
                .validate(validUser);
    }

    @Test
    public void should_encrypt_user_password_before_saving() {
        ApplicationUser validUser = ApplicationUser.builder()
                .password(ANY_VALID_PASSWORD)
                .build();

        //when
        sut.registerUser(validUser, ANY_VALID_PASSWORD_CONFIRMATION);

        //then
        then(passwordEncoder)
                .should()
                .encode(ANY_VALID_PASSWORD);
    }

}