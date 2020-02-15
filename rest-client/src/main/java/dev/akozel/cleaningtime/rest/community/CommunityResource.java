package dev.akozel.cleaningtime.rest.community;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.core.community.service.CommunityService;
import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import dev.akozel.cleaningtime.rest.common.validation.annotation.EntityExists;
import dev.akozel.cleaningtime.rest.community.dto.CommunityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
@RestController
@RequestMapping(path = "communities")
@Validated
public class CommunityResource {

    private CommunityService communityService;
    private ConversionService conversionService;

    @Autowired
    public CommunityResource(CommunityService communityService,
                             ConversionService conversionService) {
        this.communityService = communityService;
        this.conversionService = conversionService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<CommunityDto> get(@EntityExists(repository = CommunityRepository.class)
                                            @PathVariable("id") Integer id) {
        Community community = communityService.get(id);
        CommunityDto dto = conversionService.convert(community, CommunityDto.class);
        return ResponseEntity
                .ok(dto);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IdResponseDto> save(CommunityDto dto) {
        Community community = conversionService.convert(dto, Community.class);
        Integer communityId = communityService.create(community);
        IdResponseDto idResponse = conversionService.convert(communityId, IdResponseDto.class);
        return ResponseEntity
                .ok(idResponse);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<CommunityDto> update(@EntityExists(repository = CommunityRepository.class)
                                               @PathVariable("id") Integer id,
                                               @RequestBody CommunityDto dto) {
        Community community = conversionService.convert(dto, Community.class);
        Community updatedCommunity = communityService.update(id, community);
        CommunityDto updatedCommunityDto = conversionService.convert(updatedCommunity, CommunityDto.class);
        return ResponseEntity
                .ok(updatedCommunityDto);

    }

}
