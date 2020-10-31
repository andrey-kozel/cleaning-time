package dev.akozel.cleaningtime.rest.registration.mapper;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.rest.registration.dto.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper
public interface RegistrationMapper {

    ApplicationUser fromContract(RegistrationDto source);

}
