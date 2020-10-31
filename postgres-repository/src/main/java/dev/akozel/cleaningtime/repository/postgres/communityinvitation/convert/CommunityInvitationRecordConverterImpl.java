package dev.akozel.cleaningtime.repository.postgres.communityinvitation.convert;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityUserInvitationsRecord;

import javax.inject.Named;

@Named
public class CommunityInvitationRecordConverterImpl implements CommunityInvitationRecordConverter {

    @Override
    public CommunityUserInvitationsRecord convert(CommunityInvitation source) {
        CommunityUserInvitationsRecord target = new CommunityUserInvitationsRecord();
        target.setApplicationUserId(source.getUserId());
        target.setCommunityId(source.getCommunityId());
        if (source.getStatus() != null) {
            target.setStatus(source.getStatus().name());
        }
        return target;
    }

    @Override
    public CommunityInvitation unconvert(CommunityUserInvitationsRecord source) {
        if (source == null) {
            return null;
        }
        return CommunityInvitation.builder()
                .id(source.getId())
                .userId(source.getApplicationUserId())
                .communityId(source.getCommunityId())
                .status(CommunityInvitationStatus.valueOf(source.getStatus()))
                .build();
    }

}
