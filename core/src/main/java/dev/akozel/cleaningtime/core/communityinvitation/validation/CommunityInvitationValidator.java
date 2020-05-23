package dev.akozel.cleaningtime.core.communityinvitation.validation;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;

public interface CommunityInvitationValidator {

    void validateSave(Long communityId, String userEmail);

    void validateRespond(Long invitationId, CommunityInvitationStatus status);
}
