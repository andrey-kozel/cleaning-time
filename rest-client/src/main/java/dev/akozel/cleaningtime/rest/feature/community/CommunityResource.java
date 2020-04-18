package dev.akozel.cleaningtime.rest.feature.community;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.service.CommunityService;
import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import dev.akozel.cleaningtime.rest.common.dto.PaginatedItemsDto;
import dev.akozel.cleaningtime.rest.common.validation.entityexists.EntityExists;
import dev.akozel.cleaningtime.rest.feature.community.dto.CommunityDto;
import dev.akozel.cleaningtime.rest.feature.community.mapper.CommunityMapper;
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
    private final CommunityMapper communityMapper;

    @Autowired
    public CommunityResource(CommunityService communityService,
                             CommunityMapper communityMapper) {
        this.communityService = communityService;
        this.communityMapper = communityMapper;
    }

    @ApiOperation(value = "Get particular community by its ID", response = CommunityDto.class)
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<PaginatedItemsDto<CommunityDto>> find() {
        PaginatedItems<Community> communities = communityService.findByUser();
        PaginatedItemsDto<CommunityDto> dtos = communityMapper.toContract(communities);
        return ResponseEntity
                .ok(dtos);
    }

    @ApiOperation(value = "Get particular community by its ID", response = CommunityDto.class)
    @RequestMapping(method = RequestMethod.GET, path = "{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<CommunityDto> get(@EntityExists(repository = CommunityRepository.class)
                                            @PathVariable("id") Long id) {
        Community community = communityService.get(id);
        CommunityDto dto = communityMapper.toContract(community);
        return ResponseEntity
                .ok(dto);

    }

    @ApiOperation(value = "Save new community", response = IdResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IdResponseDto> save(@RequestBody CommunityDto dto) {
        Community community = communityMapper.fromContract(dto);
        Long communityId = communityService.create(community);
        IdResponseDto idResponse = communityMapper.toContract(communityId);
        return ResponseEntity
                .ok(idResponse);
    }

    @ApiOperation(value = "Update existing community", response = CommunityDto.class)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<CommunityDto> update(@EntityExists(repository = CommunityRepository.class)
                                               @PathVariable("id") Long id,
                                               @RequestBody CommunityDto dto) {
        Community community = communityMapper.fromContract(dto);
        Community updatedCommunity = communityService.update(id, community);
        CommunityDto updatedCommunityDto = communityMapper.toContract(updatedCommunity);
        return ResponseEntity
                .ok(updatedCommunityDto);

    }

}
