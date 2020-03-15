package dev.akozel.cleaningtime.repository.postgres.community.convert;

import dev.akozel.cleaningtime.core.community.domain.Community;
import org.jooq.codegen.maven.example.tables.records.CommunitiesRecord;

/**
 * CommunityConverter. Converts communities
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
public interface CommunityConverter {

    CommunitiesRecord convert(Community source);

    Community unconvert(CommunitiesRecord updatedRecord);
}