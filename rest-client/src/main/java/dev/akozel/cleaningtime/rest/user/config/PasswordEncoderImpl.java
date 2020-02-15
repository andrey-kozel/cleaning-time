package dev.akozel.cleaningtime.rest.user.config;

import dev.akozel.cleaningtime.core.user.service.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * CommunityResource. Base resource for communities
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class PasswordEncoderImpl implements PasswordEncoder {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String stringToEncode) {
        return passwordEncoder.encode(stringToEncode);
    }
}
