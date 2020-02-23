package dev.akozel.cleaningtime.rest.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * IdResponseDto.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class IdResponseDto {

    @JsonProperty("id")
    private Long id;

}
