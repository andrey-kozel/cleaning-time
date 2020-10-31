package dev.akozel.cleaningtime.rest.community;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.service.CommunityService;
import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import dev.akozel.cleaningtime.rest.common.dto.PaginatedItemsDto;
import dev.akozel.cleaningtime.rest.common.validation.entityexists.EntityExists;
import dev.akozel.cleaningtime.rest.community.dto.CommunityInDto;
import dev.akozel.cleaningtime.rest.community.dto.CommunityOutDto;
import dev.akozel.cleaningtime.rest.community.mapper.CommunityInMapper;
import dev.akozel.cleaningtime.rest.community.mapper.CommunityOutMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommunityResource. Base resource for communities
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Api(value = "Community")
@Validated
@RestController
@RequestMapping(path = "v1/communities",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CommunityResource {

    private final CommunityService communityService;
    private final CommunityInMapper communityInMapper;
    private final CommunityOutMapper communityOutMapper;

    @Autowired
    public CommunityResource(CommunityService communityService,
                             CommunityInMapper communityInMapper,
                             CommunityOutMapper communityOutMapper) {
        this.communityService = communityService;
        this.communityInMapper = communityInMapper;
        this.communityOutMapper = communityOutMapper;
    }

    @ApiOperation(value = "Get particular community by its ID", response = CommunityInDto.class)
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<PaginatedItemsDto<CommunityOutDto>> find() {
        PaginatedItems<Community> communities = communityService.findByUser();
        PaginatedItemsDto<CommunityOutDto> dtos = communityOutMapper.toContract(communities);
        return ResponseEntity
                .ok(dtos);
    }

    @ApiOperation(value = "Get particular community by its ID", response = CommunityInDto.class)
    @RequestMapping(method = RequestMethod.GET, path = "{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<CommunityOutDto> get(@EntityExists(repository = CommunityRepository.class)
                                               @PathVariable("id") Long id) {
        Community community = communityService.get(id);
        CommunityOutDto dto = communityOutMapper.toContract(community);
        return ResponseEntity
                .ok(dto);

    }

    @ApiOperation(value = "Save new community", response = IdResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IdResponseDto> create(@RequestBody CommunityInDto dto) {
        Community community = communityInMapper.fromContract(dto);
        Long communityId = communityService.create(community);
        IdResponseDto idResponse = communityInMapper.toContract(communityId);
        return ResponseEntity
                .ok(idResponse);
    }

    @ApiOperation(value = "Update existing community", response = CommunityInDto.class)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<CommunityInDto> update(@EntityExists(repository = CommunityRepository.class)
                                                 @PathVariable("id") Long id,
                                                 @RequestBody CommunityInDto dto) {
        Community community = communityInMapper.fromContract(dto);
        Community updatedCommunity = communityService.update(id, community);
        CommunityInDto updatedCommunityDto = communityInMapper.toContract(updatedCommunity);
        return ResponseEntity
                .ok(updatedCommunityDto);

    }

    @ApiOperation(value = "Delete existing community")
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}", consumes = MediaType.ALL_VALUE)
    public void delete(@EntityExists(repository = CommunityRepository.class)
                       @PathVariable("id") Long id) {
        communityService.delete(id);
    }

}
