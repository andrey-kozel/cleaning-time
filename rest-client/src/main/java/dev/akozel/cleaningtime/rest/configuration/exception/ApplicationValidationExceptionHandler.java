package dev.akozel.cleaningtime.rest.configuration.exception;

import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import dev.akozel.cleaningtime.rest.common.dto.error.ValidationResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApplicationValidationExceptionHandler. Handler for exceptions from the service layer
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@ControllerAdvice
public class ApplicationValidationExceptionHandler extends ResponseEntityExceptionHandler {

    private ConversionService conversionService;

    @Autowired
    public ApplicationValidationExceptionHandler(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @ExceptionHandler(value = ApplicationValidationException.class)
    protected ResponseEntity<Object> handleConflict(ApplicationValidationException ex, WebRequest request) {
        ValidationResultDto error = conversionService.convert(ex.getValidationResult(), ValidationResultDto.class);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

}
