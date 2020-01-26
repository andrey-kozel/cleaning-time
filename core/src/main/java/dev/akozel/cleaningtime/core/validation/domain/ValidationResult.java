package dev.akozel.cleaningtime.core.validation.domain;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidationResult.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */

@Data
public class ValidationResult {

    private List<ValidationError> errors = new ArrayList<>();

    public boolean hasErrors() {
        return CollectionUtils.isNotEmpty(errors);
    }

    public boolean appendError(ValidationError error) {
        return errors.add(error);
    }

}
