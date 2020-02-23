package dev.akozel.cleaningtime.rest.feature.community.converter;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.feature.community.dto.CommunityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * CommunityDtoConverter. Converts communities to its DTO
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class CommunityDtoConverter implements Converter<Community, CommunityDto> {

    @Override
    public CommunityDto convert(Community source) {
        CommunityDto target = new CommunityDto();
        target.setName(source.getName());
        return target;
    }
}
