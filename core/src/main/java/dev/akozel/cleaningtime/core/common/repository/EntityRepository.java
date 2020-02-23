package dev.akozel.cleaningtime.core.common.repository;

/**
 * EntityRepository.
 * <p>
 * Date: 15/012/2020
 * @param <T> - type of the entity
 * @author Andrey Kozel
 */
public interface EntityRepository<T> {

    T get(Long communityId);

}
