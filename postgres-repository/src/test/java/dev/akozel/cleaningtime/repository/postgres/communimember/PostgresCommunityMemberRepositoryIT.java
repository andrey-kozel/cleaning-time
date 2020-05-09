package dev.akozel.cleaningtime.repository.postgres.communimember;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMemberType;
import dev.akozel.cleaningtime.repository.postgres.communimember.convert.CommunityMemberRecordConverterImpl;
import dev.akozel.cleaningtime.repository.postgres.test.annotations.SqlData;
import dev.akozel.cleaningtime.repository.postgres.test.providers.DslContextProvider;
import dev.akozel.cleaningtime.repository.postgres.test.rules.PostgresContainerExecutorRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostgresCommunityMemberRepositoryIT {

    private static final Long COMMUNITY_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Integer THREE_DELETED_COMMUNITIES = 3;
    private static final CommunityMemberType OWNER = CommunityMemberType.OWNER;

    @Rule
    public final PostgresContainerExecutorRule postgresContainerExecutorRule = new PostgresContainerExecutorRule();

    private PostgresCommunityMemberRepository sut;

    @Before
    public void init() {
        sut = new PostgresCommunityMemberRepository(
                DslContextProvider.getContext(),
                new CommunityMemberRecordConverterImpl());
    }

    @Test
    @SqlData(initialScript = "communitymembers/init_community_and_user.sql")
    public void should_return_id_when_save_community_member() {
        //given
        CommunityMember member = CommunityMember.builder()
                .communityId(COMMUNITY_ID)
                .userId(USER_ID)
                .type(OWNER)
                .build();

        //when
        Long actual = sut.save(member);

        //then
        assertThat(actual)
                .isPositive();
    }

    @Test
    @SqlData(initialScript = "communitymembers/init_three_community_members.sql")
    public void should_delete_all_community_members_when_community_removed() {
        //when
        int actual = sut.deleteByCommunityId(COMMUNITY_ID);

        //then
        assertThat(actual)
                .isEqualTo(THREE_DELETED_COMMUNITIES);
    }

}