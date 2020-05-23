package dev.akozel.cleaningtime.core.communityinvitation.service;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;

public interface CommunityInvitationService {

    Long inviteUser(Long communityId, String userEmail);

    Long respondForInvitation(Long invitationId, CommunityInvitationStatus status);

}
