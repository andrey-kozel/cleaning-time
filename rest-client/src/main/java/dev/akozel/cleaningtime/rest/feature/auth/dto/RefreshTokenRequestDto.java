package dev.akozel.cleaningtime.rest.feature.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * RefreshTokenRequestDto. DTO for token refreshing
 * <p>
 * Date: 28/03/2020
 *
 * @author Andrey Kozel
 */
import javax.validation.constraints.NotEmpty;

@Data
public class RefreshTokenRequestDto {

    @NotEmpty
    @JsonProperty("accessToken")
    private String accessToken;

}
