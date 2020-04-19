package dev.akozel.cleaningtime.repository.postgres.communimember.convert;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityMembersRecord;

public interface CommunityMemberRecordConverter {

    CommunityMembersRecord convert(CommunityMember source);

    CommunityMember convert(CommunityMembersRecord source);

}
