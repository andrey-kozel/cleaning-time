package dev.akozel.cleaningtime.core.community.service;

import dev.akozel.cleaningtime.core.common.context.UserContext;
import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.validation.CommunityValidator;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * CommunityServiceImpl.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
@Named
public class CommunityServiceImpl implements CommunityService {

    private CommunityRepository communityRepository;
    private CommunityValidator communityValidator;
    private UserContext userContext;

    @Inject
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                CommunityValidator communityValidator,
                                UserContext userContext) {
        this.communityRepository = communityRepository;
        this.communityValidator = communityValidator;
        this.userContext = userContext;
    }

    @Override
    public PaginatedItems<Community> findByUser() {
        Long userId = userContext.getUserId();
        return communityRepository.find(userId);
    }

    @Override
    public Community get(Long communityId) {
        communityValidator.validateGet(communityId);
        return communityRepository.get(communityId);
    }

    @Override
    public Long create(Community community) {
        communityValidator.validateCreate(community);
        return communityRepository.save(community);
    }

    @Override
    public Community update(Long communityId, Community community) {
        communityValidator.validateUpdate(communityId, community);
        return communityRepository.update(communityId, community);
    }
}
