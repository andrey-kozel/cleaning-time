package dev.akozel.cleaningtime.repository.postgres.community;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.repository.postgres.community.convert.CommunityConverter;
import org.jooq.DSLContext;
import org.jooq.codegen.maven.example.tables.Communities;
import org.jooq.codegen.maven.example.tables.records.CommunitiesRecord;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import javax.inject.Named;

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
    private CommunityConverter converter;

    @Inject
    public PostgresCommunityRepository(DSLContext context,
                                       CommunityConverter converter) {
        this.context = context;
        this.converter = converter;
    }

    @Override
    public Long save(Community community) {
        CommunitiesRecord record = converter.convert(community);
        CommunitiesRecord savedRecord = context.insertInto(Communities.COMMUNITIES)
                .set(record)
                .returning(Communities.COMMUNITIES.ID)
                .fetchOne();
        return savedRecord.getId();
    }

    @Override
    public Community update(Long id, Community community) {
        CommunitiesRecord record = converter.convert(community);
        CommunitiesRecord updatedRecord = context.update(Communities.COMMUNITIES)
                .set(record)
                .where(Communities.COMMUNITIES.ID.eq(id))
                .returning(DSL.asterisk())
                .fetchOne();
        return converter.unconvert(updatedRecord);
    }

    @Override
    public Community get(Long communityId) {
        CommunitiesRecord updatedRecord = context.selectFrom(Communities.COMMUNITIES)
                .where(Communities.COMMUNITIES.ID.eq(communityId))
                .fetchOne();
        return converter.unconvert(updatedRecord);
    }
}
