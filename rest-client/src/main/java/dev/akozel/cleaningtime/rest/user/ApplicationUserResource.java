package dev.akozel.cleaningtime.rest.user;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;
import dev.akozel.cleaningtime.rest.common.dto.IdResponseDto;
import dev.akozel.cleaningtime.rest.community.dto.CommunityDto;
import dev.akozel.cleaningtime.rest.user.dto.ApplicationUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApplicationUserResource. Base resource for users
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Api(value = "Users")
@Validated
@RestController
@RequestMapping(path = "v1/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationUserResource {

    private ApplicationUserService applicationUserService;
    private ConversionService conversionService;

    @Autowired
    public ApplicationUserResource(ApplicationUserService applicationUserService,
                                   ConversionService conversionService) {
        this.applicationUserService = applicationUserService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Register new user", response = CommunityDto.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IdResponseDto> createUser(@RequestBody ApplicationUserDto dto) {
        ApplicationUser user = conversionService.convert(dto, ApplicationUser.class);
        Integer userId = applicationUserService.registerUser(user, dto.getPasswordConfirmation());
        IdResponseDto id = conversionService.convert(userId, IdResponseDto.class);
        return ResponseEntity
                .ok(id);
    }

}
