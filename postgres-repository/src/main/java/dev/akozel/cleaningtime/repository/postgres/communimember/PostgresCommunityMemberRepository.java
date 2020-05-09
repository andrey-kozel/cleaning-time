package dev.akozel.cleaningtime.repository.postgres.communimember;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.communitymember.repository.CommunityMemberRepository;
import dev.akozel.cleaningtime.repository.postgres.communimember.convert.CommunityMemberRecordConverter;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityMembersRecord;
import org.jooq.DSLContext;

import javax.inject.Named;

import static dev.akozel.cleaningtime.repository.postgres.tables.CommunityMembers.COMMUNITY_MEMBERS;

@Named
public class PostgresCommunityMemberRepository implements CommunityMemberRepository {

    private final DSLContext context;
    private final CommunityMemberRecordConverter converter;

    public PostgresCommunityMemberRepository(DSLContext context, CommunityMemberRecordConverter converter) {
        this.context = context;
        this.converter = converter;
    }

    @Override
    public Long save(CommunityMember member) {
        CommunityMembersRecord record = converter.convert(member);
        CommunityMembersRecord savedRecord = context.insertInto(COMMUNITY_MEMBERS)
                .set(record)
                .returning(COMMUNITY_MEMBERS.ID)
                .fetchOne();
        return savedRecord.getId();
    }

    @Override
    public int deleteByCommunityId(Long communityId) {
        return context.deleteFrom(COMMUNITY_MEMBERS)
                .where(COMMUNITY_MEMBERS.COMMUNITY_ID.eq(communityId))
                .execute();
    }
}
