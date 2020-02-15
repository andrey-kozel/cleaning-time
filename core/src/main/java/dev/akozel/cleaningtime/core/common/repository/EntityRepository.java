package dev.akozel.cleaningtime.core.common.repository;

import dev.akozel.cleaningtime.core.community.domain.Community;

public interface EntityRepository<T> {

    T get(Integer communityId);

}
