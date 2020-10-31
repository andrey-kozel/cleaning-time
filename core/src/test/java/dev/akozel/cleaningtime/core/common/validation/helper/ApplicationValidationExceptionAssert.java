package dev.akozel.cleaningtime.core.common.validation.helper;

import dev.akozel.cleaningtime.core.common.validation.domain.ValidationError;
import dev.akozel.cleaningtime.core.common.validation.exception.ApplicationValidationException;
import org.assertj.core.api.AbstractObjectAssert;

import java.util.Objects;

/**
 * ApplicationValidationExceptionAsserts.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
public class ApplicationValidationExceptionAssert
        extends AbstractObjectAssert<ApplicationValidationExceptionAssert, ApplicationValidationException> {


    public static ApplicationValidationExceptionAssert assertThat(Exception e) {
        return new ApplicationValidationExceptionAssert(e);
    }

    private ApplicationValidationExceptionAssert(Exception e) {
        super((ApplicationValidationException) e, ApplicationValidationExceptionAssert.class);
    }

    public ApplicationValidationExceptionAssert hasErrorsSize(int errorsSize) {
        isNotNull();
        int actualErrorSize = actual.getValidationResult().getErrors().size();
        if (actualErrorSize != errorsSize) {
            failWithMessage("Expected size is <%s> but actual is <%s>", errorsSize);
        }
        return this;
    }

    public ApplicationValidationExceptionAssert errorAtIndexHasFieldName(int index, String fieldName) {
        ValidationError error = actual.getValidationResult().getErrors().get(index);
        if (!Objects.equals(error.getField(), fieldName)) {
            failWithMessage("Expected field name of error at index <%s> is <%s> but was <%s>",
                    index, fieldName, error.getField());
        }
        return this;
    }

    public ApplicationValidationExceptionAssert errorAtIndexHasFieldValue(int index, Object fieldValue) {
        ValidationError error = actual.getValidationResult().getErrors().get(index);
        if (!Objects.equals(error.getValue(), fieldValue)) {
            failWithMessage("Expected field value of error at index <%s> is <%s> but was <%s>",
                    index, fieldValue, error.getValue());
        }
        return this;
    }

    public ApplicationValidationExceptionAssert errorAtIndexHasMessage(int index, String message) {
        ValidationError error = actual.getValidationResult().getErrors().get(index);
        if (!Objects.equals(error.getMessage(), message)) {
            failWithMessage("Expected message of error at index <%s> is <%s> but was <%s>",
                    index, message, error.getMessage());
        }
        return this;
    }

}
