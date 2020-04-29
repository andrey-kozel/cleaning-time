package dev.akozel.cleaningtime.core.community.service;

import dev.akozel.cleaningtime.core.common.context.UserContext;
import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.validation.CommunityValidator;
import dev.akozel.cleaningtime.core.communitymember.service.CommunityMemberService;

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

    private final CommunityRepository communityRepository;
    private final CommunityValidator communityValidator;

    private final CommunityMemberService communityMemberService;
    private final UserContext userContext;

    @Inject
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                CommunityValidator communityValidator,
                                CommunityMemberService communityMemberService,
                                UserContext userContext) {
        this.communityRepository = communityRepository;
        this.communityValidator = communityValidator;
        this.communityMemberService = communityMemberService;
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
        Long communityId = communityRepository.save(community);
        communityMemberService.createOwner(communityId);
        return communityId;
    }

    @Override
    public Community update(Long communityId, Community community) {
        communityValidator.validateUpdate(communityId, community);
        return communityRepository.update(communityId, community);
    }

    @Override
    public void delete(Long id) {
        communityValidator.validateDelete(id);
        communityMemberService.deleteByCommunityId(id);
        communityRepository.delete(id);
    }

}
