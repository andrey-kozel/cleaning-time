package dev.akozel.cleaningtime.rest.common.mapper;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import dev.akozel.cleaningtime.rest.common.dto.PaginatedItemsDto;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * BaseMapper. A set of methods that could be shared across all the mappers
 * <p>
 * Date: 15/02/2020
 *
 * @param <MODEL> - Type of the entity related to the core level
 * @param <DTO>   - Type of the entity related to the rest level
 * @author Andrey Kozel
 */
@MapperConfig
public interface BaseMapper<MODEL, DTO> {

    @Mapping(target = "id")
    IdResponseDto toContract(Long id);

    MODEL fromContract(DTO source);

    DTO toContract(MODEL source);

    List<DTO> toContract(List<MODEL> source);

    PaginatedItemsDto<DTO> toContract(PaginatedItems<MODEL> source);

}
