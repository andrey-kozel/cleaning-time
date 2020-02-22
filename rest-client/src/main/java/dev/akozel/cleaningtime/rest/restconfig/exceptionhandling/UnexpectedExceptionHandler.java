package dev.akozel.cleaningtime.rest.restconfig.exceptionhandling;

import dev.akozel.cleaningtime.rest.restconfig.exceptionhandling.dto.UnexpectedErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * UnexpectedExceptionHandler.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
public class UnexpectedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        UnexpectedErrorDto error = new UnexpectedErrorDto();
        error.setMessage(ex.getMessage());
        error.setRequestParameters(request.getParameterMap());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

}
