package dev.akozel.cleaningtime.rest.common.exceptionhandling;

import dev.akozel.cleaningtime.core.validation.domain.ValidationError;
import dev.akozel.cleaningtime.core.validation.domain.ValidationResult;
import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorEntryDto;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorResponseDto;
import dev.akozel.cleaningtime.rest.common.exceptionhandling.dto.ErrorType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ApplicationValidationException. Handles validation error from the service layer
 * <p>
 * Date: 15/03/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationValidationExceptionHandler {

    @ExceptionHandler(ApplicationValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleApplicationValidationException(ApplicationValidationException ex) {
        ErrorResponseDto errorResponseDto = buildResult(ex.getValidationResult());
        return null;
    }

    private ErrorResponseDto buildResult(ValidationResult violations) {
        List<ErrorEntryDto> errors = violations
                .getErrors()
                .stream()
                .map(this::buildError)
                .collect(Collectors.toList());
        ErrorResponseDto result = new ErrorResponseDto();
        result.setType(ErrorType.VALIDATION_ERROR);
        result.setMessage("Validation error");
        result.setDetails(errors);
        return result;
    }

    public <T> ErrorEntryDto buildError(ValidationError source) {
        ErrorEntryDto error = new ErrorEntryDto();
        error.setField(source.getField());
        error.setValue(source.getValue());
        error.setMessage(source.getMessage());
        return error;
    }

}
