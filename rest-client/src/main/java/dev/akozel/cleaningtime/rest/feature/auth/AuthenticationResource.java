package dev.akozel.cleaningtime.rest.feature.auth;

import dev.akozel.cleaningtime.rest.feature.auth.converter.AuthMapper;
import dev.akozel.cleaningtime.rest.feature.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.feature.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.feature.auth.domain.RefreshTokenRequest;
import dev.akozel.cleaningtime.rest.feature.auth.dto.AuthRequestDto;
import dev.akozel.cleaningtime.rest.feature.auth.dto.AuthResponseDto;
import dev.akozel.cleaningtime.rest.feature.auth.dto.RefreshTokenRequestDto;
import dev.akozel.cleaningtime.rest.feature.auth.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AuthenticationService authenticationService;
    private final AuthMapper authMapper;

    @Autowired
    public AuthenticationResource(AuthenticationService authenticationService,
                                  AuthMapper authMapper) {
        this.authenticationService = authenticationService;
        this.authMapper = authMapper;
    }

    @ApiOperation(value = "Generates access token for valid user", response = AuthResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDto> authenticate(@Valid @RequestBody
                                                                AuthRequestDto authRequestDto) {
        AuthRequest authRequest = authMapper.fromContract(authRequestDto);
        AuthResponse token = authenticationService.authenticate(authRequest);
        AuthResponseDto authResponseDto = authMapper.toContract(token);
        return ResponseEntity
                .ok(authResponseDto);
    }

    @ApiOperation(value = "Refreshes access token", response = AuthResponseDto.class)
    @RequestMapping(path = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDto> refreshToken(@Valid @RequestBody
                                                                RefreshTokenRequestDto dto) {
        RefreshTokenRequest request = authMapper.fromContract(dto);
        AuthResponse token = authenticationService.refreshToken(request);
        AuthResponseDto authResponse = authMapper.toContract(token);
        return ResponseEntity
                .ok(authResponse);
    }

}
