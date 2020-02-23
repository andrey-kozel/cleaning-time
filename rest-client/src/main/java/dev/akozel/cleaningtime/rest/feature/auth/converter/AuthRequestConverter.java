package dev.akozel.cleaningtime.rest.feature.auth.converter;

import dev.akozel.cleaningtime.rest.feature.auth.dto.AuthRequestDto;
import dev.akozel.cleaningtime.rest.feature.auth.domain.AuthRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * AuthRequestConverter. Converts DTO to auth request
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class AuthRequestConverter implements Converter<AuthRequestDto, AuthRequest> {

    @Override
    public AuthRequest convert(AuthRequestDto source) {
        return AuthRequest.builder()
                .email(source.getEmail())
                .password(source.getPassword())
                .build();
    }
}
