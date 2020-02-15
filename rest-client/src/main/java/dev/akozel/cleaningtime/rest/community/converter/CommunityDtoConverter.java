package dev.akozel.cleaningtime.rest.community.converter;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.community.dto.CommunityDto;
import org.springframework.core.convert.converter.Converter;

public class CommunityDtoConverter implements Converter<Community, CommunityDto> {

    @Override
    public CommunityDto convert(Community source) {
        CommunityDto target = new CommunityDto();
        target.setName(source.getName());
        return target;
    }
}
