package dev.akozel.cleaningtime.core.community.service;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.validation.RulesValidator;

import javax.inject.Inject;

/**
 * CommunityServiceImpl.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public class CommunityServiceImpl implements CommunityService {

    private CommunityRepository communityRepository;
    private RulesValidator rulesValidator;

    @Inject
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                RulesValidator rulesValidator) {
        this.communityRepository = communityRepository;
        this.rulesValidator = rulesValidator;
    }

    public Integer create(Community community) {
        rulesValidator.validate(community);

        Integer communityId = communityRepository.save(community);
        return communityId;
    }

}
