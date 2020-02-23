package dev.akozel.cleaningtime.core.community.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Community.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */

@Builder
@Data
public class Community {

    private Long id;

    @Size(min = 3)
    @NotEmpty
    private String name;

}
