package dev.akozel.cleaningtime.core.security;

/**
 * PasswordEncoder. Interface for user's password encoding
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
//TODO replace it with implementation in the core
public interface CustomPasswordEncoder {

    String encode(String stringToEncode);

    boolean matches(String rawPassword, String encodedPassword);

}
