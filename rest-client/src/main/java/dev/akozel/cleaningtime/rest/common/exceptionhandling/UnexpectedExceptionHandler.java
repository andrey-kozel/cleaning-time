package dev.akozel.cleaningtime.rest.common.exceptionhandling;

import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorResponseDto;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * UnexpectedExceptionHandler. Handles exceptions that weren't handled by the other controller advices
 * <p>
 * Date: 15/03/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
public class UnexpectedExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpectedException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setMessage("Unexpected error");
        error.setType(ErrorType.UNEXPECTED_ERROR);
        error.setMessage(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

}
