package dev.akozel.cleaningtime.core.user.service;

/**
 * PasswordEncoder. Interface for user's password encoding
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
public interface PasswordEncoder {

    String encode(String stringToEncode);

}
