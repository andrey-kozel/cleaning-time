package dev.akozel.cleaningtime.rest.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommunityDto {

    @JsonProperty("name")
    private String name;

}
