package dev.akozel.cleaningtime.rest.common.exceptionhandling;

import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorEntryDto;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorResponseDto;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ValidationExceptionHandler. Handler for validation exceptions at the resource level
 * <p>
 * Date: 15/03/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationError(MethodArgumentNotValidException ex) {
        ErrorResponseDto error = buildResult(ex.getBindingResult().getFieldErrors());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    private ErrorResponseDto buildResult(List<FieldError> violations) {
        List<ErrorEntryDto> errors = violations
                .stream()
                .map(this::buildError)
                .collect(Collectors.toList());
        ErrorResponseDto result = new ErrorResponseDto();
        result.setType(ErrorType.VALIDATION_ERROR);
        result.setDetails(errors);
        return result;
    }

    public <T> ErrorEntryDto buildError(FieldError source) {
        ErrorEntryDto target = new ErrorEntryDto();
        target.setField(source.getField());
        target.setValue(source.getRejectedValue());
        target.setMessage(source.getDefaultMessage());
        return target;
    }

}
