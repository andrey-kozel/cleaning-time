package dev.akozel.cleaningtime.rest.common.beanvalidation.entityexists;

import dev.akozel.cleaningtime.core.common.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * EntityExistsValidator. validates if entity exists
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
public class EntityExistsValidator implements ConstraintValidator<EntityExists, Long> {

    private ApplicationContext applicationContext;
    private EntityRepository<?> entityRepository;

    @Autowired
    public EntityExistsValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(EntityExists constraintAnnotation) {
        Class<? extends EntityRepository<?>> repositoryClass = constraintAnnotation.repository();
        entityRepository = applicationContext.getBean(repositoryClass);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return entityRepository.get(id) != null;
    }
}
