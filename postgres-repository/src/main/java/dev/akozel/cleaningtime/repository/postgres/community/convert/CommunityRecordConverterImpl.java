package dev.akozel.cleaningtime.repository.postgres.community.convert;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunitiesRecord;

import javax.inject.Named;

/**
 * CommunityConverterImpl.
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class CommunityRecordConverterImpl implements CommunityRecordConverter {

    @Override
    public CommunitiesRecord convert(Community source) {
        CommunitiesRecord target = new CommunitiesRecord();
        target.setName(source.getName());
        return target;
    }

    @Override
    public Community unconvert(CommunitiesRecord updatedRecord) {
        if (updatedRecord == null) {
            return null;
        }
        return Community.builder()
                .id(updatedRecord.getId())
                .name(updatedRecord.getName())
                .build();
    }
}
