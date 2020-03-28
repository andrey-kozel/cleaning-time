package dev.akozel.cleaningtime.rest.feature.auth.domain;

import lombok.Data;

/**
 * RefreshTokenRequest. Request for token refresh
 * <p>
 * Date: 28/03/2020
 *
 * @author Andrey Kozel
 */
@Data
public class RefreshTokenRequest {

    private String accessToken;

}
