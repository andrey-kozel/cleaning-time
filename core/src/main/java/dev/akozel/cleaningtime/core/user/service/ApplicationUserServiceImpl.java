package dev.akozel.cleaningtime.core.user.service;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.repository.ApplicationUserRepository;
import dev.akozel.cleaningtime.core.validation.RulesValidator;

/**
 * ApplicationUserServiceImpl.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private RulesValidator rulesValidator;
    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(RulesValidator rulesValidator,
                                      ApplicationUserRepository applicationUserRepository) {
        this.rulesValidator = rulesValidator;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public Integer createUser(ApplicationUser user) {
        rulesValidator.validate(user);
        return applicationUserRepository.save(user);
    }
}
