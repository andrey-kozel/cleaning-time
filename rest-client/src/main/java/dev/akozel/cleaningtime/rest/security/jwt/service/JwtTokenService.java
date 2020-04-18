package dev.akozel.cleaningtime.rest.security.jwt.service;

import dev.akozel.cleaningtime.rest.feature.auth.model.ApplicationUserDetails;

/**
 * JwtTokenManager. Class for generation and validation of JWT token
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
public interface JwtTokenService {

    String generateAccessToken(ApplicationUserDetails user);

    String getUsernameByAccessToken(String jwtToken);

}
