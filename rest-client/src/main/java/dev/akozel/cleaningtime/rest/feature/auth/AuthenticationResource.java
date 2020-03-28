package dev.akozel.cleaningtime.rest.feature.auth;

import dev.akozel.cleaningtime.rest.feature.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.feature.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.feature.auth.domain.RefreshTokenRequest;
import dev.akozel.cleaningtime.rest.feature.auth.dto.AuthRequestDto;
import dev.akozel.cleaningtime.rest.feature.auth.dto.AuthResponseDto;
import dev.akozel.cleaningtime.rest.feature.auth.dto.RefreshTokenRequestDto;
import dev.akozel.cleaningtime.rest.feature.auth.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * AuthenticationResource. Resource that generates access tokn based on the user
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@RestController
@RequestMapping("v1/auth")
public class AuthenticationResource {

    private AuthenticationService authenticationService;
    private ConversionService conversionService;

    @Autowired
    public AuthenticationResource(AuthenticationService authenticationService, ConversionService conversionService) {
        this.authenticationService = authenticationService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Generates access token for valid user", response = AuthResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDto> authenticate(@Valid @RequestBody
                                                                AuthRequestDto authRequestDto) {
        AuthRequest authRequest = conversionService.convert(authRequestDto, AuthRequest.class);
        AuthResponse token = authenticationService.authenticate(authRequest);
        AuthResponseDto authResponseDto = conversionService.convert(token, AuthResponseDto.class);
        return ResponseEntity
                .ok(authResponseDto);
    }

    @ApiOperation(value = "Refreshes access token", response = AuthResponseDto.class)
    @RequestMapping(path = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDto> refreshToken(@Valid @RequestBody
                                                                RefreshTokenRequestDto dto) {
        RefreshTokenRequest request = conversionService.convert(dto, RefreshTokenRequest.class);
        AuthResponse token = authenticationService.refreshToken(request);
        AuthResponseDto authResponse = conversionService.convert(token, AuthResponseDto.class);
        return ResponseEntity
                .ok(authResponse);
    }

}