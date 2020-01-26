package dev.akozel.cleaningtime.core.community.repository;

import dev.akozel.cleaningtime.core.community.domain.Community;

/**
 * CommunityRepository.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityRepository {

    Integer save(Community community);

    Community get(Integer communityId);

}
