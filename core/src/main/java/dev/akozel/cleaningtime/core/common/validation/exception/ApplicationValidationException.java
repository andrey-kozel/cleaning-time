package dev.akozel.cleaningtime.core.common.validation.exception;

import dev.akozel.cleaningtime.core.common.validation.domain.ValidationResult;
import lombok.Getter;

/**
 * ValidationException.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */

@Getter
public class ApplicationValidationException extends RuntimeException {

    private ValidationResult validationResult;

    public ApplicationValidationException(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }
}
