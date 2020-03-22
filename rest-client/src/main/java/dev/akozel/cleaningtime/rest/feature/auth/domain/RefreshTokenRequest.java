package dev.akozel.cleaningtime.rest.feature.auth.domain;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String accessToken;

}
