package dev.akozel.cleaningtime.core.community.service;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import org.springframework.transaction.annotation.Transactional;

/**
 * CommunityService.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityService {

    Community get(Long id);

    @Transactional
    Long create(Community community);

    Community update(Long id, Community community);

    PaginatedItems<Community> findByUser();

}
