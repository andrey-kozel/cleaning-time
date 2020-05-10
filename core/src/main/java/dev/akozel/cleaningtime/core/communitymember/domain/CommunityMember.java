package dev.akozel.cleaningtime.core.communitymember.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * CommunityMember. Domain model for community members
 * <p>
 * Date: 18/04/2020
 *
 * @author Andrey Kozel
 */
@Data
@Builder
public class CommunityMember {

    private Long id;

    @NotNull
    private Long communityId;

    @NotNull
    private Long userId;

    @NotNull
    private CommunityMemberType type;

}
