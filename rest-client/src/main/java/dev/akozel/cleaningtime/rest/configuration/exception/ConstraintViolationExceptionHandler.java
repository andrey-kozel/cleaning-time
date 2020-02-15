package dev.akozel.cleaningtime.rest.configuration.exception;

import dev.akozel.cleaningtime.rest.common.dto.error.ValidationErrorDto;
import dev.akozel.cleaningtime.rest.common.dto.error.ValidationResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ConstraintViolationExceptionHandler.
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConflict(ConstraintViolationException ex) {
        ValidationResultDto error = buildResult(ex.getConstraintViolations());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    private <T> ValidationResultDto buildResult(Set<ConstraintViolation<?>> violations) {
        List<ValidationErrorDto> errors = violations
                .stream()
                .map(this::buildError)
                .collect(Collectors.toList());
        ValidationResultDto result = new ValidationResultDto();
        result.setErrors(errors);
        return result;
    }

    public <T> ValidationErrorDto buildError(ConstraintViolation<T> violation) {
        ValidationErrorDto error = new ValidationErrorDto();
        error.setField(violation.getPropertyPath().toString());
        error.setValue(violation.getInvalidValue());
        error.setMessage(violation.getMessage());
        return error;
    }


}
