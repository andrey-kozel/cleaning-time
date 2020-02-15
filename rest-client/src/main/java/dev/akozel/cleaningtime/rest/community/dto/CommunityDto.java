package dev.akozel.cleaningtime.rest.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * CommunityDto. Community Dto for REST responses and requests
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class CommunityDto {

    @JsonProperty("name")
    private String name;

}
