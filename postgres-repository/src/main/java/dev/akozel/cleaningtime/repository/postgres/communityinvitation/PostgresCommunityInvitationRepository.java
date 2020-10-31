package dev.akozel.cleaningtime.repository.postgres.communityinvitation;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.core.communityinvitation.repository.CommunityInvitationRepository;
import dev.akozel.cleaningtime.repository.postgres.communityinvitation.convert.CommunityInvitationRecordConverter;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityUserInvitationsRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import javax.inject.Named;

import static dev.akozel.cleaningtime.repository.postgres.tables.CommunityUserInvitations.COMMUNITY_USER_INVITATIONS;

@Named
public class PostgresCommunityInvitationRepository implements CommunityInvitationRepository {

    private final DSLContext context;
    private final CommunityInvitationRecordConverter converter;

    @Inject
    public PostgresCommunityInvitationRepository(DSLContext context,
                                                 CommunityInvitationRecordConverter converter) {
        this.context = context;
        this.converter = converter;
    }

    @Override
    public Long save(CommunityInvitation communityInvitation) {
        CommunityUserInvitationsRecord record = converter.convert(communityInvitation);
        CommunityUserInvitationsRecord savedRecord = context.insertInto(COMMUNITY_USER_INVITATIONS)
                .set(record)
                .returning(COMMUNITY_USER_INVITATIONS.ID)
                .fetchOne();
        return savedRecord.getId();
    }

    @Override
    public CommunityInvitation update(Long id, CommunityInvitationStatus newStatus) {
        CommunityUserInvitationsRecord updatedRecord = context.update(COMMUNITY_USER_INVITATIONS)
                .set(COMMUNITY_USER_INVITATIONS.STATUS, newStatus.name())
                .where(COMMUNITY_USER_INVITATIONS.ID.eq(id))
                .returning(DSL.asterisk())
                .fetchOne();
        return converter.unconvert(updatedRecord);
    }

    @Override
    public CommunityInvitation findByCommunityAndUser(Long communityId, Long userId) {
        CommunityUserInvitationsRecord record = context.selectFrom(COMMUNITY_USER_INVITATIONS)
                .where(COMMUNITY_USER_INVITATIONS.COMMUNITY_ID.eq(communityId))
                .and(COMMUNITY_USER_INVITATIONS.APPLICATION_USER_ID.eq(userId))
                .fetchOne();
        return converter.unconvert(record);
    }

    @Override
    public CommunityInvitation get(Long invitationId) {
        CommunityUserInvitationsRecord record = context.selectFrom(COMMUNITY_USER_INVITATIONS)
                .where(COMMUNITY_USER_INVITATIONS.ID.eq(invitationId))
                .fetchOne();
        return converter.unconvert(record);
    }

    @Override
    public int delete(Long invitationId) {
        return context.deleteFrom(COMMUNITY_USER_INVITATIONS)
                .where(COMMUNITY_USER_INVITATIONS.ID.eq(invitationId))
                .execute();
    }
}
