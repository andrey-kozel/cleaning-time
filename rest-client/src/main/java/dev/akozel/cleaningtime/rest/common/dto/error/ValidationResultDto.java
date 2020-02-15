package dev.akozel.cleaningtime.rest.common.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidationResultDto.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class ValidationResultDto {

    @JsonProperty("errors")
    private List<ValidationErrorDto> errors = new ArrayList<>();

}
