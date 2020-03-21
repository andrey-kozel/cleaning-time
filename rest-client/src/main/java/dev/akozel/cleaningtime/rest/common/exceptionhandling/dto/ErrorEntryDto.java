package dev.akozel.cleaningtime.rest.common.exceptionhandling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * ErrorEntryDto. Represents error entry in the response
 * <p>
 * Date: 15/03/2020
 *
 * @author Andrey Kozel
 */
@Data
public class ErrorEntryDto {

    @JsonProperty("field")
    private String field;

    @JsonProperty("value")
    private Object value;

    @JsonProperty("message")
    private String message;

}
