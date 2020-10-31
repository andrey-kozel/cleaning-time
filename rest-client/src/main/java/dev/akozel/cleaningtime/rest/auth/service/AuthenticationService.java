package dev.akozel.cleaningtime.rest.auth.service;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.rest.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.auth.domain.AuthResponse;
import dev.akozel.cleaningtime.rest.auth.domain.RefreshTokenRequest;

/**
 * AuthenticationService. This service responsible for validation of the user and token generation
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
public interface AuthenticationService {

    void registerUser(ApplicationUser user, String passwordConfirmation);

    AuthResponse authenticate(AuthRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);
}
