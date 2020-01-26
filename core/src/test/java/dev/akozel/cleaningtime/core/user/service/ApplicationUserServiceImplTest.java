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

    @Mock
    private RulesValidator rulesValidator;
    @Mock
    private ApplicationUserRepository applicationUserRepository;
    @InjectMocks
    private ApplicationUserServiceImpl sut;

    @Test
    public void should_save_user_when_user_is_valid() {
        //given
        ApplicationUser validUser = ApplicationUser.builder()
                .build();

        //when
        sut.createUser(validUser);

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
        sut.createUser(validUser);

        //then
        then(rulesValidator)
                .should()
                .validate(validUser);
    }

}