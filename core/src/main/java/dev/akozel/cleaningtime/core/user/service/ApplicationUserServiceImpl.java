package dev.akozel.cleaningtime.core.user.service;

import dev.akozel.cleaningtime.core.security.Encoder;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.repository.ApplicationUserRepository;
import dev.akozel.cleaningtime.core.user.validation.UserValidator;

import javax.inject.Named;

/**
 * ApplicationUserServiceImpl.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
@Named
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private UserValidator userValidator;
    private ApplicationUserRepository applicationUserRepository;
    private Encoder passwordEncoder;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
                                      UserValidator userValidator,
                                      Encoder passwordEncoder) {
        this.userValidator = userValidator;
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long saveUser(ApplicationUser user) {
        userValidator.validateSave(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return applicationUserRepository.save(user);
    }

    @Override
    public ApplicationUser getByEmail(String email) {
        userValidator.validateGetByEmail(email);
        return applicationUserRepository.getByEmail(email);
    }
}
