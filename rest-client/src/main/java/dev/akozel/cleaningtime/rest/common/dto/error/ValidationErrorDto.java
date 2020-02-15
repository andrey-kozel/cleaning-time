package dev.akozel.cleaningtime.rest.common.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValidationErrorDto {

    @JsonProperty("field")
    private String field;

    @JsonProperty("value")
    private Object value;

    @JsonProperty("message")
    private String message;

}
