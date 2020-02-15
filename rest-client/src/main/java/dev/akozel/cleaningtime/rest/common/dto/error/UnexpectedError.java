package dev.akozel.cleaningtime.rest.common.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * UnexpectedError.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class UnexpectedError {

    @JsonProperty("message")
    private String message;

    @JsonProperty("requestParameters")
    private Map<String, String[]> requestParameters;

}
