package dev.akozel.cleaningtime.core.communityinvitation.repository;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;

public interface CommunityInvitationRepository {

    Long save(CommunityInvitation communityInvitation);

    CommunityInvitation update(Long invitationId, CommunityInvitationStatus newStatus);

    CommunityInvitation findByCommunityAndUser(Long communityId, Long userId);

    CommunityInvitation get(Long invitationId);

    int delete(Long invitationId);

}
