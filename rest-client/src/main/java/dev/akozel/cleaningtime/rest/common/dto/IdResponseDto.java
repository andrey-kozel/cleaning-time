package dev.akozel.cleaningtime.rest.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IdResponseDto {

    @JsonProperty("id")
    private Integer id;

}
