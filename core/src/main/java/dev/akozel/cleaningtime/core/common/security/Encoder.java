package dev.akozel.cleaningtime.core.common.security;

/**
 * Encoder. Interface for user's password encoding
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
public interface Encoder {

    String encode(String stringToEncode);

    boolean matches(String rawPassword, String encodedPassword);

}
