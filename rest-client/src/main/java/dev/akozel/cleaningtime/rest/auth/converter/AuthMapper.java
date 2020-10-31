package dev.akozel.cleaningtime.rest.auth.converter;

import dev.akozel.cleaningtime.rest.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.auth.domain.RefreshTokenRequest;
import dev.akozel.cleaningtime.rest.auth.dto.AuthRequestDto;
import dev.akozel.cleaningtime.rest.auth.dto.AuthResponseDto;
import dev.akozel.cleaningtime.rest.auth.dto.RefreshTokenRequestDto;
import org.mapstruct.Mapper;

/**
 * AuthMapper. Mapper for the authorization payloads
 * <p>
 * Date: 12/04/2020
 *
 * @author Andrey Kozel
 */
@Mapper
public interface AuthMapper {

    AuthRequest fromContract(AuthRequestDto source);

    AuthResponseDto toContract(AuthResponse source);

    RefreshTokenRequest fromContract(RefreshTokenRequestDto source);

}
