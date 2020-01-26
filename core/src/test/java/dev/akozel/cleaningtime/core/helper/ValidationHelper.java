package dev.akozel.cleaningtime.core.helper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * ValidationHelper.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
public class ValidationHelper {

    private Validator validator;

    public ValidationHelper() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public <T> Set<ConstraintViolation<T>> validate(T objectToBeValidated) {
        return validator.validate(objectToBeValidated);
    }

}
