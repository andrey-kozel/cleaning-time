package dev.akozel.cleaningtime.repository.postgres.communimember.convert;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMemberType;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityMembersRecord;

import javax.inject.Named;

@Named
public class CommunityMemberRecordConverterImpl implements CommunityMemberRecordConverter {

    @Override
    public CommunityMembersRecord convert(CommunityMember source) {
        CommunityMembersRecord target = new CommunityMembersRecord();
        target.setCommunityId(source.getCommunityId());
        target.setType(source.getType().name());
        target.setApplicationUserId(source.getUserId());
        return target;
    }

    @Override
    public CommunityMember convert(CommunityMembersRecord source) {
        return CommunityMember.builder()
                .communityId(source.getCommunityId())
                .userId(source.getApplicationUserId())
                .type(CommunityMemberType.valueOf(source.getType()))
                .build();
    }

}
