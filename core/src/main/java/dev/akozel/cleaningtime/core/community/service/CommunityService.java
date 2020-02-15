package dev.akozel.cleaningtime.core.community.service;

import dev.akozel.cleaningtime.core.community.domain.Community;

/**
 * CommunityService.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityService {

    Community get(Integer id);

    Integer create(Community community);

    Community update(Integer id, Community community);
}
