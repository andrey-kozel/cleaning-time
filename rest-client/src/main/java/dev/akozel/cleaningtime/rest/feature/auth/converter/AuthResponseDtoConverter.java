package dev.akozel.cleaningtime.rest.feature.auth.converter;

import dev.akozel.cleaningtime.rest.feature.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.feature.auth.dto.AuthResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * AuthResponseDtoConverter. Converts Auth response to DTO
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class AuthResponseDtoConverter implements Converter<AuthResponse, AuthResponseDto> {

    @Override
    public AuthResponseDto convert(AuthResponse source) {
        AuthResponseDto target = new AuthResponseDto();
        target.setAccessToken(source.getAccessToken());
        return target;
    }
}
