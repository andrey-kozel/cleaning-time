package dev.akozel.cleaningtime.rest.common.exceptionhandling;

import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorResponseDto;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorType;
import dev.akozel.cleaningtime.rest.auth.exception.InvalidJwtTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * InvalidJwtTokenHandler. HAndles exceptions related to wrong formatted JWT token
 * <p>
 * Date: 12/04/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
public class InvalidJwtTokenHandler {

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidJwtTokenException(InvalidJwtTokenException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setType(ErrorType.INVALID_TOKEN);
        error.setMessage(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

}
