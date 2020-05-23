package dev.akozel.cleaningtime.core.communityinvitation.domain;

import lombok.Data;

@Data
public class CommunityInvitationLog {

    private Long id;
    private Long invitationId;
    private Long submittedAt;
    private CommunityInvitationStatus status;

}
