package dev.akozel.cleaningtime.rest.common.converter;

import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import org.springframework.core.convert.converter.Converter;

public class IdResponseConverter implements Converter<Integer, IdResponseDto> {

    @Override
    public IdResponseDto convert(Integer source) {
        IdResponseDto target = new IdResponseDto();
        target.setId(source);
        return target;
    }
}
