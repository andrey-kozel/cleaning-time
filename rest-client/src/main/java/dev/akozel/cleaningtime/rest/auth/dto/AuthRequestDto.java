package dev.akozel.cleaningtime.rest.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * AuthRequestDto. DTO form auth request
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class AuthRequestDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
