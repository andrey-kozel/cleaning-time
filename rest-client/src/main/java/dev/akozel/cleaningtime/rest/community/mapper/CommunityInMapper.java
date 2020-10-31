package dev.akozel.cleaningtime.rest.community.mapper;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.common.mapping.BaseMapper;
import dev.akozel.cleaningtime.rest.community.dto.CommunityInDto;
import org.mapstruct.Mapper;

/**
 * CommunityConverter. Converter for communitiesDto to communities
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Mapper
public interface CommunityInMapper extends BaseMapper<Community, CommunityInDto> {

}
