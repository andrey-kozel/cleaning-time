package dev.akozel.cleaningtime.core.communitymember.service;

/**
 * CommunityMemberService. Business logic related to community members
 * <p>
 * Date: 18/04/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityMemberService {

    Long createOwner(Long communityId);

    Long createMember(Long communityId, Long userId);

    void deleteByCommunityId(Long communityId);
}
