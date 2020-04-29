package dev.akozel.cleaningtime.rest.feature.community.mapper;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.common.mapper.BaseMapper;
import dev.akozel.cleaningtime.rest.feature.community.dto.CommunityOutDto;
import org.mapstruct.Mapper;

@Mapper
public interface CommunityOutMapper extends BaseMapper<Community, CommunityOutDto> {

}
