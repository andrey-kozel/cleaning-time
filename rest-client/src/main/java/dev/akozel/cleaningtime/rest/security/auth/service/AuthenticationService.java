package dev.akozel.cleaningtime.rest.security.auth.service;

import dev.akozel.cleaningtime.rest.security.auth.domain.AuthRequest;
import dev.akozel.cleaningtime.rest.security.auth.domain.AuthResponse;

/**
 * AuthenticationService. This service responsible for validation of the user and token generation
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
public interface AuthenticationService {

    AuthResponse authenticate(AuthRequest request);

}
