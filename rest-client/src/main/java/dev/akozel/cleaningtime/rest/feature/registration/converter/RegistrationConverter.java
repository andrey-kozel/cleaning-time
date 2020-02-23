package dev.akozel.cleaningtime.rest.feature.registration.converter;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.rest.feature.registration.dto.RegistrationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * ApplicationUserConverter.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class RegistrationConverter implements Converter<RegistrationDto, ApplicationUser> {

    @Override
    public ApplicationUser convert(RegistrationDto source) {
        return ApplicationUser.builder()
                .email(source.getEmail())
                .password(source.getPassword())
                .build();
    }
}
