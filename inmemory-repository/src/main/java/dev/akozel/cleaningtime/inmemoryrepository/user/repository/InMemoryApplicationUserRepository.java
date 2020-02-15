package dev.akozel.cleaningtime.inmemoryrepository.user.repository;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.repository.ApplicationUserRepository;
import dev.akozel.cleaningtime.inmemoryrepository.helper.IdGenerationHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * InMemoryApplicationUserRepository.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
public class InMemoryApplicationUserRepository implements ApplicationUserRepository {

    private static final List<ApplicationUser> APPLICATION_USERS = new ArrayList<>();

    private IdGenerationHelper idGenerationHelper;

    public InMemoryApplicationUserRepository(IdGenerationHelper idGenerationHelper) {
        this.idGenerationHelper = idGenerationHelper;
    }

    @Override
    public Integer save(ApplicationUser user) {
        Integer nextUserId = idGenerationHelper.generateId();
        user.setId(nextUserId);
        APPLICATION_USERS.add(user);
        return nextUserId;
    }
}
