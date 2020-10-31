package dev.akozel.cleaningtime.repository.postgres.communityinvitation.convert;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityUserInvitationsRecord;

public interface CommunityInvitationRecordConverter {

    CommunityUserInvitationsRecord convert(CommunityInvitation source);

    CommunityInvitation unconvert(CommunityUserInvitationsRecord source);

}
