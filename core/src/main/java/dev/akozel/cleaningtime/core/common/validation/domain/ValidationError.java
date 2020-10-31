package dev.akozel.cleaningtime.core.common.validation.domain;

import lombok.Data;

/**
 * ValidationError.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */

@Data
public class ValidationError {

    private String field;
    private Object value;
    private String message;

}
