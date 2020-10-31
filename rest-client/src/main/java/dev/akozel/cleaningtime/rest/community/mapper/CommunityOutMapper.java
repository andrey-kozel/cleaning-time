package dev.akozel.cleaningtime.rest.community.mapper;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.common.mapping.BaseMapper;
import dev.akozel.cleaningtime.rest.community.dto.CommunityOutDto;
import org.mapstruct.Mapper;

@Mapper
public interface CommunityOutMapper extends BaseMapper<Community, CommunityOutDto> {

}
