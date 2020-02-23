package dev.akozel.cleaningtime.core.community.validation;

import dev.akozel.cleaningtime.core.community.domain.Community;

/**
 * CommunityValidator. Validator for community service
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityValidator {

    void validateGet(Integer id);

    void validateCreate(Community community);

    void validateUpdate(Integer communityId, Community community);

}
