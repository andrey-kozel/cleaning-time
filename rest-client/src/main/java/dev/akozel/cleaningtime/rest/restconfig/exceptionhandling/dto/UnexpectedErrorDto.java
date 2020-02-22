package dev.akozel.cleaningtime.rest.restconfig.exceptionhandling.dto;

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
public class UnexpectedErrorDto {

    @JsonProperty("message")
    private String message;

    @JsonProperty("requestParameters")
    private Map<String, String[]> requestParameters;

}
