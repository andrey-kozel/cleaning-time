package dev.akozel.cleaningtime.rest.common.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResultDto {

    @JsonProperty("errors")
    private List<ValidationErrorDto> errors = new ArrayList<>();

}
