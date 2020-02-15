package dev.akozel.cleaningtime.inmemoryrepository.community.repository;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.inmemoryrepository.helper.IdGenerationHelper;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * InmemoryCommunityRepository.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
@Named
public class InMemoryCommunityRepository implements CommunityRepository {

    private static final List<Community> COMMUNITIES = new ArrayList<>();

    private IdGenerationHelper idGenerationHelper;

    public InMemoryCommunityRepository(IdGenerationHelper idGenerationHelper) {
        this.idGenerationHelper = idGenerationHelper;
    }

    @Override
    public Integer save(Community community) {
        Integer id = idGenerationHelper.generateId();
        community.setId(id);
        COMMUNITIES.add(community);
        return id;
    }

    @Override
    public Community get(Integer communityId) {
        return COMMUNITIES.stream()
                .filter(community -> Objects.equals(community.getId(), communityId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Community update(Integer id, Community community) {
        community.setId(id);
        COMMUNITIES.removeIf(existingCommunity -> Objects.equals(existingCommunity.getId(), id));
        COMMUNITIES.add(community);
        return community;
    }
}
