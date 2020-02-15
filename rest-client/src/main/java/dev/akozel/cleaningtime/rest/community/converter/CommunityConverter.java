package dev.akozel.cleaningtime.rest.community.converter;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.community.dto.CommunityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommunityConverter implements Converter<CommunityDto, Community> {

    @Override
    public Community convert(CommunityDto source) {
        return Community.builder()
                .name(source.getName())
                .build();
    }
}
