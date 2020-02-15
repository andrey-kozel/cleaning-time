package dev.akozel.cleaningtime.rest.user.converter;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.rest.user.dto.ApplicationUserDto;
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
public class ApplicationUserConverter implements Converter<ApplicationUserDto, ApplicationUser> {

    @Override
    public ApplicationUser convert(ApplicationUserDto source) {
        return ApplicationUser.builder()
                .email(source.getEmail())
                .password(source.getPassword())
                .build();
    }
}
