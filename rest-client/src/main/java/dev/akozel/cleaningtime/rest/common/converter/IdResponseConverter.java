package dev.akozel.cleaningtime.rest.common.converter;

import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * IdResponseConverter.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class IdResponseConverter implements Converter<Long, IdResponseDto> {

    @Override
    public IdResponseDto convert(Long source) {
        IdResponseDto target = new IdResponseDto();
        target.setId(source);
        return target;
    }
}
