package dev.akozel.cleaningtime.core.communitymember.repository;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;

/**
 * CommunityMemberRepository. Interface for working with community members persistence
 * <p>
 * Date: 18/04/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityMemberRepository {

    Long save(CommunityMember member);

    int deleteByCommunityId(Long communityId);
}
