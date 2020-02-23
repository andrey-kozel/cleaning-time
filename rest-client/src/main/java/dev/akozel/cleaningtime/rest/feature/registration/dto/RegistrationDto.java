package dev.akozel.cleaningtime.rest.feature.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * ApplicationUserDto. Application user DTO for rest requests and response
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class RegistrationDto {

    @JsonProperty("login")
    @Size(min = 5)
    @NotEmpty
    private String email;

    @JsonProperty("password")
    @Size(min = 5)
    @NotEmpty
    private String password;

    @JsonProperty("passwordConfirmation")
    @Size(min = 5)
    @NotEmpty
    private String passwordConfirmation;

}
