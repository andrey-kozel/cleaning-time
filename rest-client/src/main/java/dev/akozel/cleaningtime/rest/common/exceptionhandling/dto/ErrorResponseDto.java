package dev.akozel.cleaningtime.rest.common.exceptionhandling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * ErrorResponseDto. Error details
 * <p>
 * Date: 15/03/2020
 *
 * @author Andrey Kozel
 */
@Data
public class ErrorResponseDto {

    @JsonProperty("type")
    private ErrorType type;

    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    private List<ErrorEntryDto> details;

}
