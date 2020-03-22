package dev.akozel.cleaningtime.rest.feature.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RefreshTokenRequestDto {

    @NotEmpty
    @JsonProperty("accessToken")
    private String accessToken;

}
