package dev.akozel.cleaningtime.core.helper;

import org.assertj.core.api.AbstractObjectAssert;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * ConstraintViolationAssert.
 * <p>
 * Date: 26/01/2020
 *
 * @author Andrey Kozel
 */
public class ConstraintViolationAssert<T>
        extends AbstractObjectAssert<ConstraintViolationAssert<T>, List<ConstraintViolation<T>>> {

    public ConstraintViolationAssert(Collection<ConstraintViolation<T>> constraintViolations) {
        super(new ArrayList<>(constraintViolations), ConstraintViolationAssert.class);
    }

    public static <T> ConstraintViolationAssert<T> assertThat(Collection<ConstraintViolation<T>> constraintViolations) {
        return new ConstraintViolationAssert<>(constraintViolations);
    }

    public ConstraintViolationAssert<T> hasViolationSize(int expectedSize) {
        isNotNull();
        if (actual.size() != expectedSize) {
            failWithMessage("Wrong violations amount. Expected: <%s>, but was: <%s>", expectedSize, actual.size());
        }
        return this;
    }

    public ConstraintViolationAssert<T> hasMoreViolationsThan(int expectedSize) {
        isNotNull();
        if (actual.size() < expectedSize) {
            failWithMessage("Wrong violations amount. Expected at least: <%s>, but was: <%s>", expectedSize, actual.size());
        }
        return this;
    }

    public ConstraintViolationAssert<T> hasWrongValueAtIndex(int index, String invalidValue) {
        isNotNull();
        ConstraintViolation<T> violation = actual.get(index);
        if (!Objects.equals(violation.getInvalidValue(), invalidValue)) {
            failWithMessage("Wrong violation's value. Expected: <%s>, but was: <%s>",
                    invalidValue, violation.getInvalidValue());
        }
        return this;
    }
}
