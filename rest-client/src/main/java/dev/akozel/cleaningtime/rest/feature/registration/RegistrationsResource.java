package dev.akozel.cleaningtime.rest.feature.registration;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.rest.feature.auth.service.AuthenticationService;
import dev.akozel.cleaningtime.rest.feature.community.dto.CommunityDto;
import dev.akozel.cleaningtime.rest.feature.registration.dto.RegistrationDto;
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
@RequestMapping(path = "v1/registrations",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationsResource {

    private AuthenticationService authenticationService;
    private ConversionService conversionService;

    @Autowired
    public RegistrationsResource(AuthenticationService authenticationService,
                                 ConversionService conversionService) {
        this.authenticationService = authenticationService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Register new user", response = CommunityDto.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody RegistrationDto dto) {
        ApplicationUser user = conversionService.convert(dto, ApplicationUser.class);
        authenticationService.registerUser(user, dto.getPasswordConfirmation());
        return ResponseEntity
                .noContent()
                .build();
    }

}
