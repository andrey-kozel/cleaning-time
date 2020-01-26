package dev.akozel.cleaningtime.core.user.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * ApplicationUser.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */

@Builder
@Data
public class ApplicationUser {

    private Integer id;

    @Size(min = 5)
    @NotEmpty
    private String login;

    @Size(min = 5)
    @NotEmpty
    private String password;

    @Size(min = 5)
    @NotEmpty
    private String firstName;

    @Size(min = 5)
    @NotEmpty
    private String lastName;

}
