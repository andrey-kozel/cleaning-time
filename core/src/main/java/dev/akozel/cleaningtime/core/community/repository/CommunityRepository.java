package dev.akozel.cleaningtime.core.community.repository;

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

    Integer save(Community community);

    Community update(Integer id, Community community);

}
