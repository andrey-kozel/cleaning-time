package dev.akozel.cleaningtime.core.community.service;

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

    @Inject
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                CommunityValidator communityValidator) {
        this.communityRepository = communityRepository;
        this.communityValidator = communityValidator;
    }

    @Override
    public Community get(Integer communityId) {
        communityValidator.validateGet(communityId);
        return communityRepository.get(communityId);
    }

    @Override
    public Integer create(Community community) {
        communityValidator.validateCreate(community);
        return communityRepository.save(community);
    }

    @Override
    public Community update(Integer communityId, Community community) {
        communityValidator.validateUpdate(communityId, community);
        return communityRepository.update(communityId, community);
    }
}
