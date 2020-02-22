package dev.akozel.cleaningtime.rest.restconfig.exceptionhandling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * ValidationErrorDto.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class ValidationErrorDto {

    @JsonProperty("field")
    private String field;

    @JsonProperty("value")
    private Object value;

    @JsonProperty("message")
    private String message;

}
