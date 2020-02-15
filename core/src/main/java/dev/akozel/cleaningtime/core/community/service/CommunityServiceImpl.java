package dev.akozel.cleaningtime.core.community.service;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.validation.RulesValidator;

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
    private RulesValidator rulesValidator;

    @Inject
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                RulesValidator rulesValidator) {
        this.communityRepository = communityRepository;
        this.rulesValidator = rulesValidator;
    }

    @Override
    public Community get(Integer communityId) {
        if (communityId == null) {
            rulesValidator.raiseError("Community id should be present", "communityId", null);
        }
        return communityRepository.get(communityId);
    }

    @Override
    public Integer create(Community community) {
        rulesValidator.validate(community);

        Integer communityId = communityRepository.save(community);
        return communityId;
    }

    @Override
    public Community update(Integer communityId, Community community) {
        if (communityId == null) {
            rulesValidator.raiseError("Community id should be present", "communityId", null);
        }
        rulesValidator.validate(community);
        return communityRepository.update(communityId, community);
    }
}
