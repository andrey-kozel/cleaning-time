package dev.akozel.cleaningtime.rest.feature.auth.converter;

import dev.akozel.cleaningtime.rest.feature.auth.domain.RefreshTokenRequest;
import dev.akozel.cleaningtime.rest.feature.auth.dto.RefreshTokenRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenRequestConverter implements Converter<RefreshTokenRequestDto, RefreshTokenRequest> {

    @Override
    public RefreshTokenRequest convert(RefreshTokenRequestDto source) {
        RefreshTokenRequest target = new RefreshTokenRequest();
        target.setAccessToken(source.getAccessToken());
        return target;
    }
}
