package dev.akozel.cleaningtime.rest.common.validation.annotation;

import dev.akozel.cleaningtime.core.common.repository.EntityRepository;
import dev.akozel.cleaningtime.rest.common.validation.validator.EntityExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EntityExists. Marks the entities that must be presented in a storage
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EntityExistsValidator.class)
public @interface EntityExists {

    String message() default "Entity must exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends EntityRepository<?>> repository();

}
