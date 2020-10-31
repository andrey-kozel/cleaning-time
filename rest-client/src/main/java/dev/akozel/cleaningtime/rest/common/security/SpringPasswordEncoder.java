package dev.akozel.cleaningtime.rest.common.security;

import dev.akozel.cleaningtime.core.common.security.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * SpringPasswordEncoder.
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class SpringPasswordEncoder implements PasswordEncoder {

    private Encoder encoder;

    @Autowired
    public SpringPasswordEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword.toString(), encodedPassword.toString());
    }
}
