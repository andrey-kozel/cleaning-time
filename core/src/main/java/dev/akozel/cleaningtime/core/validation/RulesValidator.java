package dev.akozel.cleaningtime.core.validation;

/**
 * Validator.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public interface RulesValidator {

    <T> void validate(T entityForValidation);

    void raiseError(String message, String field, Object value);

}
