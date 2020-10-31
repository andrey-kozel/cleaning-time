package dev.akozel.cleaningtime.core.common.security;

import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Named;

/**
 * BCryptEncoder. Bcrypt implementation of the encoder
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class BCryptEncoder implements Encoder {

    @Override
    public String encode(String stringToEncode) {
        return BCrypt.hashpw(stringToEncode, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
