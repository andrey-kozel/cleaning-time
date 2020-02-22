package dev.akozel.cleaningtime.rest.security.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * JwtTokenManager. Class for generation and validation of JWT token
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
public interface JwtTokenService {

    String generateAccessToken(UserDetails user);

    String getUsernameByAccessToken(String jwtToken);

}
