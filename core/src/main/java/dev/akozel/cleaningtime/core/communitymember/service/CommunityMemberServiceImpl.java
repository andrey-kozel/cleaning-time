package dev.akozel.cleaningtime.core.communitymember.service;

import dev.akozel.cleaningtime.core.common.context.UserContext;
import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMemberType;
import dev.akozel.cleaningtime.core.communitymember.repository.CommunityMemberRepository;
import dev.akozel.cleaningtime.core.communitymember.validation.CommunityMemberValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CommunityMemberServiceImpl implements CommunityMemberService {

    private final CommunityMemberValidator communityMemberValidator;
    private final CommunityMemberRepository communityMemberRepository;
    private final UserContext userContext;

    @Inject
    public CommunityMemberServiceImpl(CommunityMemberValidator communityMemberValidator,
                                      CommunityMemberRepository communityMemberRepository,
                                      UserContext userContext) {
        this.communityMemberValidator = communityMemberValidator;
        this.communityMemberRepository = communityMemberRepository;
        this.userContext = userContext;
    }

    @Override
    public Long createOwner(Long communityId) {
        CommunityMember owner = CommunityMember.builder()
                .communityId(communityId)
                .userId(userContext.getUserId())
                .type(CommunityMemberType.OWNER)
                .build();
        communityMemberValidator.validateCreate(owner);
        return communityMemberRepository.save(owner);
    }

    @Override
    public Long createMember(Long communityId, Long userId) {
        CommunityMember member = CommunityMember.builder()
                .communityId(communityId)
                .userId(userId)
                .type(CommunityMemberType.MEMBER)
                .build();
        communityMemberValidator.validateCreate(member);
        return communityMemberRepository.save(member);
    }

    @Override
    public void deleteByCommunityId(Long communityId) {
        communityMemberRepository.deleteByCommunityId(communityId);
    }
}
