package dev.akozel.cleaningtime.repository.postgres.community;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.repository.postgres.community.convert.CommunityRecordConverter;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunitiesRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

import static dev.akozel.cleaningtime.repository.postgres.tables.Communities.COMMUNITIES;
import static dev.akozel.cleaningtime.repository.postgres.tables.CommunitiesMember.COMMUNITIES_MEMBER;

/**
 * PostgresCommunityRepository. Saves communities
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class PostgresCommunityRepository implements CommunityRepository {

    private DSLContext context;
    private CommunityRecordConverter converter;

    @Inject
    public PostgresCommunityRepository(DSLContext context,
                                       CommunityRecordConverter converter) {
        this.context = context;
        this.converter = converter;
    }

    @Override
    public Long save(Community community) {
        CommunitiesRecord record = converter.convert(community);
        CommunitiesRecord savedRecord = context.insertInto(COMMUNITIES)
                .set(record)
                .returning(COMMUNITIES.ID)
                .fetchOne();
        return savedRecord.getId();
    }

    @Override
    public Community update(Long id, Community community) {
        CommunitiesRecord record = converter.convert(community);
        CommunitiesRecord updatedRecord = context.update(COMMUNITIES)
                .set(record)
                .where(COMMUNITIES.ID.eq(id))
                .returning(DSL.asterisk())
                .fetchOne();
        return converter.unconvert(updatedRecord);
    }

    @Override
    public Community get(Long communityId) {
        CommunitiesRecord updatedRecord = context.selectFrom(COMMUNITIES)
                .where(COMMUNITIES.ID.eq(communityId))
                .fetchOne();
        return converter.unconvert(updatedRecord);
    }

    @Override
    public PaginatedItems<Community> find(Long userId) {
        List<Community> communities = findItems(userId);
        Long communitiesCount = findCount(userId);
        return PaginatedItems.of(communities, communitiesCount);
    }

    private List<Community> findItems(Long userId) {
        return context
                .selectFrom(COMMUNITIES
                        .join(COMMUNITIES_MEMBER)
                        .onKey(COMMUNITIES_MEMBER.COMMUNITY_ID))
                .where(COMMUNITIES_MEMBER.APPLICATION_USER_ID.eq(userId))
                .fetchInto(COMMUNITIES)
                .stream()
                .map(converter::unconvert)
                .collect(Collectors.toList());
    }

    private Long findCount(Long userId) {
        return context
                .selectCount()
                .from(COMMUNITIES)
                .join(COMMUNITIES_MEMBER)
                .on(COMMUNITIES.ID.eq(COMMUNITIES_MEMBER.COMMUNITY_ID))
                .where(COMMUNITIES_MEMBER.APPLICATION_USER_ID.eq(userId))
                .fetchOne(0, Long.class);
    }

}
