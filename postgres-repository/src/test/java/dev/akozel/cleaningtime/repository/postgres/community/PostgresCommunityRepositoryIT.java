package dev.akozel.cleaningtime.repository.postgres.community;

import dev.akozel.cleaningtime.core.common.model.PaginatedItems;
import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.repository.postgres.community.convert.CommunityRecordConverterImpl;
import dev.akozel.cleaningtime.repository.postgres.test.annotations.SqlData;
import dev.akozel.cleaningtime.repository.postgres.test.providers.DslContextProvider;
import dev.akozel.cleaningtime.repository.postgres.test.rules.PostgresContainerExecutorRule;
import org.jooq.exception.DataAccessException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

public class PostgresCommunityRepositoryIT {

    private static final Long EXISTING_COMMUNITY_ID = 1L;
    private static final Long MISSING_COMMUNITY_ID = 5L;
    private static final Long USER_ID_WITH_THREE_COMMUNITIES = 1L;
    private static final Integer ONE_DELETED_ITEM = 1;

    private static final String NULL = null;
    private static final String VALID_COMMUNITY_NAME = "valid_community_name";
    private static final String NEW_COMMUNITY_NAME = "new_community_name";

    @Rule
    public final PostgresContainerExecutorRule postgresContainerExecutorRule = new PostgresContainerExecutorRule();

    private PostgresCommunityRepository sut;

    @Before
    public void init() {
        sut = new PostgresCommunityRepository(DslContextProvider.getContext(), new CommunityRecordConverterImpl());
    }

    @Test
    public void should_return_null_when_community_with_provided_id_not_exists() {
        //when
        Community actual = sut.get(MISSING_COMMUNITY_ID);

        //then
        assertNull(actual);
    }

    @Test
    @SqlData(initialScript = "communities/insert_community.sql")
    public void should_return_community_when_provided_id_exists() {
        //when
        Community actual = sut.get(EXISTING_COMMUNITY_ID);

        //then
        assertThat(actual)
                .hasFieldOrPropertyWithValue("name", "community_name");
    }

    @Test
    @SqlData(initialScript = "communities/insert_for_user.sql")
    public void should_find_all_user_community_when_id_is_provided() {
        //when
        PaginatedItems<Community> communities = sut.find(USER_ID_WITH_THREE_COMMUNITIES);

        //then
        assertThat(communities.getTotal())
                .isEqualTo(3);
        assertThat(communities.getItems())
                .hasSize(3);
    }

    @Test
    public void should_return_id_when_saves_new_community() {
        //given
        Community community = Community.builder()
                .name(VALID_COMMUNITY_NAME)
                .build();

        //when
        Long actual = sut.save(community);

        //then
        assertThat(actual)
                .isPositive();
    }

    @Test
    public void should_throw_an_exception_when_name_is_null() {
        //given
        Community community = Community.builder()
                .name(NULL)
                .build();

        //when
        when(() -> sut.save(community));

        //then
        assertThat(caughtException())
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("null value in column \"name\" violates not-null constraint");
    }

    @Test
    @SqlData(initialScript = "communities/insert_community.sql")
    public void should_return_updated_community_when_updates_it() {
        //given
        Community community = Community.builder()
                .name(NEW_COMMUNITY_NAME)
                .build();

        //when
        Community actual = sut.update(EXISTING_COMMUNITY_ID, community);

        //then
        assertThat(actual)
                .hasFieldOrPropertyWithValue("name", NEW_COMMUNITY_NAME);
    }

    @Test
    @SqlData(initialScript = "communities/insert_community.sql")
    public void should_return_one_when_community_deleted_successfully() {
        //when
        int actual = sut.delete(EXISTING_COMMUNITY_ID);

        //then
        assertThat(actual)
                .isEqualTo(ONE_DELETED_ITEM);
    }

}