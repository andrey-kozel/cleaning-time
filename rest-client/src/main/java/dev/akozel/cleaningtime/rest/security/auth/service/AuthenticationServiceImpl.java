package dev.akozel.cleaningtime.rest.security.auth.service;

import dev.akozel.cleaningtime.core.security.CustomPasswordEncoder;
import dev.akozel.cleaningtime.rest.security.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.security.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.security.jwt.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

    private UserDetailsService userDetailsService;
    private CustomPasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;

    @Autowired
    public AuthenticationServiceImpl(UserDetailsService userDetailsService,
                                     CustomPasswordEncoder passwordEncoder,
                                     JwtTokenService jwtTokenService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        String accessToken = jwtTokenService.generateAccessToken(user);
        return new AuthResponse(accessToken);
    }

}
