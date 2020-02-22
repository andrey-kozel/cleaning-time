package dev.akozel.cleaningtime.rest.security.jwt.exception;

/**
 * InvalidJwtTokenException.
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
public class InvalidJwtTokenException extends RuntimeException {

    public InvalidJwtTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
