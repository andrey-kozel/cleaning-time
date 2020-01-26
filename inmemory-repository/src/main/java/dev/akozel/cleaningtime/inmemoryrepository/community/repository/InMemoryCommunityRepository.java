package dev.akozel.cleaningtime.inmemoryrepository.community.repository;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.repository.CommunityRepository;
import dev.akozel.cleaningtime.inmemoryrepository.community.helper.CommunityIdGenerationHelper;

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
public class InMemoryCommunityRepository implements CommunityRepository {

    private static final List<Community> COMMUNITIES = new ArrayList<>();

    private CommunityIdGenerationHelper communityIdGenerationHelper;

    public InMemoryCommunityRepository(CommunityIdGenerationHelper communityIdGenerationHelper) {
        this.communityIdGenerationHelper = communityIdGenerationHelper;
    }

    @Override
    public Integer save(Community community) {
        Integer id = communityIdGenerationHelper.generateId();
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
}
