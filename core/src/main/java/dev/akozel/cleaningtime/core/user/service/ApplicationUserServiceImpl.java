package dev.akozel.cleaningtime.core.user.service;

import dev.akozel.cleaningtime.core.security.Encoder;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.repository.ApplicationUserRepository;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;
import java.util.Objects;

/**
 * ApplicationUserServiceImpl.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
@Named
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private RulesValidator rulesValidator;
    private ApplicationUserRepository applicationUserRepository;
    private Encoder passwordEncoder;

    public ApplicationUserServiceImpl(RulesValidator rulesValidator,
                                      ApplicationUserRepository applicationUserRepository,
                                      Encoder passwordEncoder) {
        this.rulesValidator = rulesValidator;
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Integer registerUser(ApplicationUser user, String passwordConfirmation) {
        rulesValidator.validate(user);
        if (!Objects.equals(user.getPassword(), passwordConfirmation)) {
            rulesValidator.raiseError("Passwords are not match", "password", user.getPassword());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return applicationUserRepository.save(user);
    }

    @Override
    public ApplicationUser getByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            rulesValidator.raiseError("Email must not be empty", "email", email);
        }
        return applicationUserRepository.getByEmail(email);
    }
}
