package dev.akozel.cleaningtime.rest.auth.service;

import dev.akozel.cleaningtime.core.common.security.Encoder;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;
import dev.akozel.cleaningtime.rest.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.auth.domain.RefreshTokenRequest;
import dev.akozel.cleaningtime.rest.auth.model.ApplicationUserDetails;
import dev.akozel.cleaningtime.rest.auth.validation.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * AuthenticationServiceImpl. Default implementation
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthValidator authValidator;
    private final UserDetailsService userDetailsService;
    private final ApplicationUserService applicationUserService;
    private final Encoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthenticationServiceImpl(AuthValidator authValidator,
                                     UserDetailsService userDetailsService,
                                     ApplicationUserService applicationUserService,
                                     Encoder passwordEncoder,
                                     JwtTokenService jwtTokenService) {
        this.authValidator = authValidator;
        this.userDetailsService = userDetailsService;
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void registerUser(ApplicationUser user, String passwordConfirmation) {
        authValidator.validateRegistration(user, passwordConfirmation);
        applicationUserService.saveUser(user);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        ApplicationUserDetails user = loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        String accessToken = jwtTokenService.generateAccessToken(user);
        return new AuthResponse(accessToken);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String userName = jwtTokenService.getUsernameByAccessToken(request.getAccessToken());
        ApplicationUserDetails user = loadUserByUsername(userName);
        String accessToken = jwtTokenService.generateAccessToken(user);
        return new AuthResponse(accessToken);
    }

    private ApplicationUserDetails loadUserByUsername(String email) {
        return (ApplicationUserDetails) userDetailsService.loadUserByUsername(email);
    }

}
