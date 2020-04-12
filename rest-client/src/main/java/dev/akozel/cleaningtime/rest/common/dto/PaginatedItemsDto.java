package dev.akozel.cleaningtime.rest.common.dto;

import lombok.Data;

import java.util.List;

/**
 * PaginatedItemsDto. DTO for paginated items
 * <p>
 * Date: 15/02/2020
 *
 * @param <T> - type of the DTO
 * @author Andrey Kozel
 */
@Data
public class PaginatedItemsDto<T> {

    private List<T> items;
    private Long total;

}
