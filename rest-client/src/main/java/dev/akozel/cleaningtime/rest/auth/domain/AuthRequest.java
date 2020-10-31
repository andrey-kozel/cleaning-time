package dev.akozel.cleaningtime.rest.auth.domain;

import lombok.Builder;
import lombok.Data;

/**
 * AuthRequest. Domain model for authorization request
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Builder
@Data
public class AuthRequest {

    private String email;
    private String password;

}
