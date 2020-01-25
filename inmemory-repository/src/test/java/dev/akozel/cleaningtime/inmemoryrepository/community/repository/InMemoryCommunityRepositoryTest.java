package dev.akozel.cleaningtime.inmemoryrepository.community.repository;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.inmemoryrepository.community.helper.CommunityIdGenerationHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class InMemoryCommunityRepositoryTest {

    private static final String VALID_COMMUNITY_NAME = "ANY_VALID_NAME";
    private static final Integer MISSING_ID = -1;
    private static final Integer NEXT_ID = 432534;

    @Mock
    private CommunityIdGenerationHelper communityIdGenerationHelper;
    @InjectMocks
    private InMemoryCommunityRepository sut;

    @Test
    public void should_set_id_for_saved_community() {
        //given

        Community validCommunity = Community.builder()
                .name(VALID_COMMUNITY_NAME)
                .build();
        given(communityIdGenerationHelper.generateId())
                .willReturn(NEXT_ID);

        //when
        sut.save(validCommunity);

        //then
        assertThat(validCommunity.getId())
                .isEqualTo(validCommunity.getId());
    }

    @Test
    public void should_return_item_id_when_item_saved() {
        //given
        Community validCommunity = Community.builder()
                .name(VALID_COMMUNITY_NAME)
                .build();
        given(communityIdGenerationHelper.generateId())
                .willReturn(NEXT_ID);

        //when
        Integer actual = sut.save(validCommunity);

        //then
        assertThat(actual)
                .isEqualTo(NEXT_ID);
    }

    @Test
    public void should_return_null_when_item_with_given_id_not_exists() {
        //when
        Community community = sut.get(MISSING_ID);

        assertThat(community)
                .isNull();
    }

    @Test
    public void should_return_community_with_provided_id_when_it_exists() {
        //given
        Community community = Community.builder()
                .name(VALID_COMMUNITY_NAME)
                .build();

        //when
        Integer actualId = sut.save(community);
        Community actual = sut.get(actualId);

        //then
        assertThat(actualId)
                .isEqualTo(actual.getId());
        assertThat(actual)
                .isSameAs(community);
    }

}