package dev.akozel.cleaningtime.rest.configuration.converter;

import dev.akozel.cleaningtime.core.validation.domain.ValidationError;
import dev.akozel.cleaningtime.core.validation.domain.ValidationResult;
import dev.akozel.cleaningtime.rest.common.dto.error.ValidationErrorDto;
import dev.akozel.cleaningtime.rest.common.dto.error.ValidationResultDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ValidationErrorConverter. Converter for validation errors
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class ValidationErrorConverter implements Converter<ValidationResult, ValidationResultDto> {

    @Override
    public ValidationResultDto convert(ValidationResult source) {
        List<ValidationErrorDto> errors = source.getErrors()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
        ValidationResultDto target = new ValidationResultDto();
        target.setErrors(errors);
        return target;
    }

    private ValidationErrorDto convert(ValidationError error) {
        ValidationErrorDto target = new ValidationErrorDto();
        target.setField(error.getField());
        target.setMessage(error.getMessage());
        target.setValue(error.getValue());
        return target;
    }

}