package dev.akozel.cleaningtime.core.validation;

import dev.akozel.cleaningtime.core.validation.domain.ValidationError;
import dev.akozel.cleaningtime.core.validation.domain.ValidationResult;
import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ValidatorImpl.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
@Named
public class RulesValidatorImpl implements RulesValidator {

    private Validator validator;

    public RulesValidatorImpl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public <T> void validate(T entityForValidation) {
        Set<ConstraintViolation<T>> violations = validator.validate(entityForValidation);
        ValidationResult validationResult = buildResult(violations);
        if (validationResult.hasErrors()) {
            throw new ApplicationValidationException(validationResult);
        }
    }

    @Override
    public void raiseError(String message, String field, Object value) {
        ValidationError error = new ValidationError();
        error.setMessage(message);
        error.setField(field);
        error.setValue(value);
        ValidationResult result = new ValidationResult();
        result.appendError(error);
        throw new ApplicationValidationException(result);
    }

    private <T> ValidationResult buildResult(Set<ConstraintViolation<T>> violations) {
        List<ValidationError> errors = violations
                .stream()
                .map(this::buildError)
                .collect(Collectors.toList());
        ValidationResult result = new ValidationResult();
        result.setErrors(errors);
        return result;
    }

    public <T> ValidationError buildError(ConstraintViolation<T> violation) {
        ValidationError error = new ValidationError();
        error.setField(violation.getPropertyPath().toString());
        error.setValue(violation.getInvalidValue());
        error.setMessage(violation.getMessage());
        return error;
    }

}
