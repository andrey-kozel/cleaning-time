package dev.akozel.cleaningtime.core.community.repository;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.common.repository.EntityRepository;
import dev.akozel.cleaningtime.core.community.domain.Community;

/**
 * CommunityRepository.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityRepository extends EntityRepository<Community> {

    Long save(Community community);

    Community update(Long id, Community community);

    PaginatedItems<Community> find(Long userId);
}
