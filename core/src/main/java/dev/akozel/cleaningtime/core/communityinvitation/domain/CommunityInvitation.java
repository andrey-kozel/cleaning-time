package dev.akozel.cleaningtime.core.communityinvitation.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunityInvitation {

    private Long id;
    private Long communityId;
    private Long userId;
    private CommunityInvitationStatus status;

}
