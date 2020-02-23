package dev.akozel.cleaningtime.repository.postgres.community.convert;

import dev.akozel.cleaningtime.core.community.domain.Community;
import org.jooq.codegen.maven.example.tables.records.CommunitiesRecord;

import javax.inject.Named;

/**
 * CommunityConverterImpl.
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class CommunityConverterImpl implements CommunityConverter {

    @Override
    public CommunitiesRecord convert(Community source) {
        CommunitiesRecord target = new CommunitiesRecord();
        target.setName(source.getName());
        return target;
    }

    @Override
    public Community unconvert(CommunitiesRecord updatedRecord) {
        return Community.builder()
                .id(updatedRecord.getId())
                .name(updatedRecord.getName())
                .build();
    }
}
