package dev.akozel.cleaningtime.rest.common.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class UnexpectedError {

    @JsonProperty("message")
    private String message;

    @JsonProperty("requestParameters")
    private Map<String, String[]> requestParameters;

}
