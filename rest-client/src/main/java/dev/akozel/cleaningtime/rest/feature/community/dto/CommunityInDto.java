package dev.akozel.cleaningtime.rest.feature.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * CommunityDto. Community Dto for REST responses and requests
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Data
public class CommunityInDto {

    @NotEmpty
    @JsonProperty("name")
    private String name;

}
