package dev.akozel.cleaningtime.repository.postgres.communityinvitation;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.repository.postgres.communityinvitation.convert.CommunityInvitationRecordConverterImpl;
import dev.akozel.cleaningtime.repository.postgres.tables.records.CommunityUserInvitationsLogRecord;
import dev.akozel.cleaningtime.repository.postgres.test.annotations.SqlData;
import dev.akozel.cleaningtime.repository.postgres.test.providers.DslContextProvider;
import dev.akozel.cleaningtime.repository.postgres.test.rules.PostgresContainerExecutorRule;
import org.jooq.exception.DataAccessException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static dev.akozel.cleaningtime.repository.postgres.tables.CommunityUserInvitationsLog.COMMUNITY_USER_INVITATIONS_LOG;
import static org.assertj.core.api.Assertions.assertThat;

public class PostgresCommunityInvitationRepositoryIT {

    private static final Long EXISTING_USER_ID = 1L;
    private static final Long EXISTING_COMMUNITY_ID = 1L;
    private static final Long EXISTING_INVITATION_ID = 1L;
    private static final CommunityInvitationStatus EXISTING_STATUS = CommunityInvitationStatus.SUBMITTED;
    private static final CommunityInvitationStatus ANY_VALID_STATUS = CommunityInvitationStatus.SUBMITTED;
    private static final CommunityInvitationStatus OTHER_VALID_STATUS = CommunityInvitationStatus.REJECTED;

    private static final Long INVALID_USER_ID = null;
    private static final Long INVALID_COMMUNITY_ID = null;
    private static final Long MISSING_INVITATION_ID = 556L;
    private static final CommunityInvitationStatus INVALID_STATUS = null;

    @Rule
    public final PostgresContainerExecutorRule postgresContainerExecutorRule = new PostgresContainerExecutorRule();

    private PostgresCommunityInvitationRepository sut;

    @Before
    public void init() {
        sut = new PostgresCommunityInvitationRepository(
                DslContextProvider.getContext(),
                new CommunityInvitationRecordConverterImpl());
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_and_user.sql")
    public void should_return_id_invitation_when_save_community_with_required_fields_filled() {
        //given
        CommunityInvitation invitation = CommunityInvitation.builder()
                .communityId(EXISTING_COMMUNITY_ID)
                .userId(EXISTING_USER_ID)
                .status(ANY_VALID_STATUS)
                .build();

        //when
        Long actual = sut.save(invitation);

        //then
        assertThat(actual)
                .isNotNull()
                .isPositive();
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_and_user.sql")
    public void should_create_community_log_when_invitation_saved_successfully() {
        //given
        CommunityInvitation invitation = CommunityInvitation.builder()
                .communityId(EXISTING_COMMUNITY_ID)
                .userId(EXISTING_USER_ID)
                .status(ANY_VALID_STATUS)
                .build();

        //when
        Long invitationId = sut.save(invitation);
        CommunityUserInvitationsLogRecord actual = DslContextProvider.getContext()
                .selectFrom(COMMUNITY_USER_INVITATIONS_LOG)
                .where(COMMUNITY_USER_INVITATIONS_LOG.INVITATION_ID.eq(invitationId))
                .fetchOne();

        //then
        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("invitationId", invitationId);
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community.sql")
    public void should_throw_error_when_save_without_user_id() {
        //given
        CommunityInvitation invitation = CommunityInvitation.builder()
                .communityId(EXISTING_COMMUNITY_ID)
                .userId(INVALID_USER_ID)
                .status(ANY_VALID_STATUS)
                .build();

        //when
        when(() -> sut.save(invitation));

        //then
        assertThat(caughtException())
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("null value in column \"application_user_id\" violates not-null constraint");
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_user.sql")
    public void should_throw_error_when_save_without_community_id() {
        //given
        CommunityInvitation invitation = CommunityInvitation.builder()
                .communityId(INVALID_COMMUNITY_ID)
                .userId(EXISTING_USER_ID)
                .status(ANY_VALID_STATUS)
                .build();

        //when
        when(() -> sut.save(invitation));

        //then
        assertThat(caughtException())
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("null value in column \"community_id\" violates not-null constraint");
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_and_user.sql")
    public void should_throw_error_when_save_without_status() {
        //given
        CommunityInvitation invitation = CommunityInvitation.builder()
                .communityId(EXISTING_COMMUNITY_ID)
                .userId(EXISTING_USER_ID)
                .status(INVALID_STATUS)
                .build();

        //when
        when(() -> sut.save(invitation));

        //then
        assertThat(caughtException())
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("null value in column \"status\" violates not-null constraint");
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_user_and_invitation.sql")
    public void should_create_log_message_when_invitation_is_updated() {
        //when
        sut.update(EXISTING_INVITATION_ID, OTHER_VALID_STATUS);
        CommunityUserInvitationsLogRecord actual = DslContextProvider.getContext()
                .selectFrom(COMMUNITY_USER_INVITATIONS_LOG)
                .where(COMMUNITY_USER_INVITATIONS_LOG.INVITATION_ID.eq(EXISTING_INVITATION_ID))
                .and(COMMUNITY_USER_INVITATIONS_LOG.STATUS.eq(OTHER_VALID_STATUS.name()))
                .fetchOne();

        //then
        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("status", OTHER_VALID_STATUS.name());

    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_and_user.sql")
    public void should_throw_an_exception_when_tries_to_submit_more_than_one_invitation_to_the_user() {
        //given
        CommunityInvitation invitation = CommunityInvitation.builder()
                .communityId(EXISTING_COMMUNITY_ID)
                .userId(EXISTING_USER_ID)
                .status(OTHER_VALID_STATUS)
                .build();

        //when
        sut.save(invitation);
        when(() -> sut.save(invitation));

        //then
        assertThat(caughtException())
                .isInstanceOf(DataAccessException.class)
                .hasMessageContaining("Key (community_id, application_user_id)=(1, 1) already exists.");
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_user_and_invitation.sql")
    public void should_return_invitation_when_invitation_for_user_and_community_exists() {
        //when
        CommunityInvitation actual = sut.findByCommunityAndUser(EXISTING_COMMUNITY_ID, EXISTING_USER_ID);

        //then
        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("userId", EXISTING_USER_ID)
                .hasFieldOrPropertyWithValue("communityId", EXISTING_COMMUNITY_ID)
                .hasFieldOrPropertyWithValue("status", EXISTING_STATUS)
        ;
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_and_user.sql")
    public void should_return_null_when_invitation_for_user_and_community_was_not_found() {
        //when
        CommunityInvitation actual = sut.findByCommunityAndUser(EXISTING_COMMUNITY_ID, EXISTING_USER_ID);

        //then
        assertThat(actual)
                .isNull();
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_user_and_invitation.sql")
    public void should_return_invitation_when_it_could_be_found_by_id() {
        //when
        CommunityInvitation actual = sut.get(EXISTING_INVITATION_ID);

        //then
        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("communityId", EXISTING_COMMUNITY_ID)
                .hasFieldOrPropertyWithValue("userId", EXISTING_USER_ID)
                .hasFieldOrPropertyWithValue("status", EXISTING_STATUS);
    }

    @Test
    public void should_return_null_if_invitation_doesnt_exists() {
        //when
        CommunityInvitation actual = sut.get(MISSING_INVITATION_ID);

        //then
        assertThat(actual)
                .isNull();
    }

    @Test
    @SqlData(initialScript = "communityinvitation/init_community_user_and_invitation.sql")
    public void should_return_one_when_invitation_deleted_successfully() {
        //when
        int actual = sut.delete(EXISTING_INVITATION_ID);

        //then
        assertThat(actual)
                .isEqualTo(1);
    }

    @Test
    public void should_return_zero_when_invitation_deleted_successfully() {
        //when
        int actual = sut.delete(MISSING_INVITATION_ID);

        //then
        assertThat(actual)
                .isEqualTo(0);
    }

}