package dev.akozel.cleaningtime.rest.security.password;

import dev.akozel.cleaningtime.core.security.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * CommunityResource. Base resource for communities
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class ApplicationPasswordEncoderImpl implements CustomPasswordEncoder {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationPasswordEncoderImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String stringToEncode) {
        return passwordEncoder.encode(stringToEncode);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
