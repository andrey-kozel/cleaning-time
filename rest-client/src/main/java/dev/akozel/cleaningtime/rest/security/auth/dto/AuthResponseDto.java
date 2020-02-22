package dev.akozel.cleaningtime.rest.security.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * AuthResponseDto. DTO for auth response with access token
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class AuthResponseDto {

    @JsonProperty("accessToken")
    private String accessToken;

}
