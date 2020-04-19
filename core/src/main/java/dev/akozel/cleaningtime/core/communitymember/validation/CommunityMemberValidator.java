package dev.akozel.cleaningtime.core.communitymember.validation;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;

public interface CommunityMemberValidator {

    void validateCreate(CommunityMember communityMember);

}
