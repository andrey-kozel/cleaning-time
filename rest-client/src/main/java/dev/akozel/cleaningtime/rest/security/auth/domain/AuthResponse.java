package dev.akozel.cleaningtime.rest.security.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AuthResponse. Wrapper for access token
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;

}
