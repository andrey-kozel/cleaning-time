package dev.akozel.cleaningtime.rest.community.converter;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.rest.community.dto.CommunityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * CommunityConverter. Converter for communitiesDto to communities
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class CommunityConverter implements Converter<CommunityDto, Community> {

    @Override
    public Community convert(CommunityDto source) {
        return Community.builder()
                .name(source.getName())
                .build();
    }
}
