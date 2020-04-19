package dev.akozel.cleaningtime.core.communitymember.service;

import dev.akozel.cleaningtime.core.common.context.UserContext;
import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMemberType;
import dev.akozel.cleaningtime.core.communitymember.repository.CommunityMemberRepository;
import dev.akozel.cleaningtime.core.communitymember.validation.CommunityMemberValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CommunityMemberServiceImplTest {

    private static final Long ANY_VALID_COMMUNITY_ID = 173567L;
    private static final Long ANY_VALID_USER_ID = 573638L;
    private static final Long ANY_VALID_COMMUNITY_MEMBER_ID = 756483L;

    @Mock
    private CommunityMemberValidator communityMemberValidator;
    @Mock
    private CommunityMemberRepository communityMemberRepository;
    @Mock
    private UserContext userContext;
    @InjectMocks
    private CommunityMemberServiceImpl sut;

    @Captor
    private ArgumentCaptor<CommunityMember> memberArgumentCaptor;


    @Test
    public void should_validate_owner_before_create_owner() {
        //when
        sut.createOwner(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityMemberValidator)
                .should()
                .validateCreate(isA(CommunityMember.class));
    }

    @Test
    public void should_return_id_when_create_owner() {
        //given
        given(communityMemberRepository.save(isA(CommunityMember.class)))
                .willReturn(ANY_VALID_COMMUNITY_MEMBER_ID);
        //when
        Long actualId = sut.createOwner(ANY_VALID_COMMUNITY_ID);

        //then
        assertThat(actualId)
                .isEqualTo(ANY_VALID_COMMUNITY_MEMBER_ID);
    }

    @Test
    public void should_set_user_id_from_context_when_create_owner() {
        //given
        given(userContext.getUserId())
                .willReturn(ANY_VALID_USER_ID);

        //when
        sut.createOwner(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityMemberRepository)
                .should()
                .save(memberArgumentCaptor.capture());

        assertThat(memberArgumentCaptor.getValue())
                .hasFieldOrPropertyWithValue("userId", ANY_VALID_USER_ID);
    }

    @Test
    public void should_set_community_and_type_before_save_owner() {
        //when
        sut.createOwner(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityMemberRepository)
                .should()
                .save(memberArgumentCaptor.capture());

        assertThat(memberArgumentCaptor.getValue())
                .hasFieldOrPropertyWithValue("communityId", ANY_VALID_COMMUNITY_ID)
                .hasFieldOrPropertyWithValue("type", CommunityMemberType.OWNER);
    }

    @Test
    public void should_validate_member_before_create_member() {
        //when
        sut.createOwner(ANY_VALID_COMMUNITY_ID);

        //then
        then(communityMemberValidator)
                .should()
                .validateCreate(isA(CommunityMember.class));
    }

    @Test
    public void should_return_member_id_when_create_member() {
        //given
        given(communityMemberRepository.save(isA(CommunityMember.class)))
                .willReturn(ANY_VALID_COMMUNITY_MEMBER_ID);
        //when
        Long actualId = sut.createMember(ANY_VALID_COMMUNITY_ID, ANY_VALID_USER_ID);

        //then
        assertThat(actualId)
                .isEqualTo(ANY_VALID_COMMUNITY_MEMBER_ID);
    }

    @Test
    public void should_fill_all_required_fields_when_create_member() {
        //when
        sut.createMember(ANY_VALID_COMMUNITY_ID, ANY_VALID_USER_ID);

        //then
        then(communityMemberRepository)
                .should()
                .save(memberArgumentCaptor.capture());

        assertThat(memberArgumentCaptor.getValue())
                .hasFieldOrPropertyWithValue("userId", ANY_VALID_USER_ID)
                .hasFieldOrPropertyWithValue("communityId", ANY_VALID_COMMUNITY_ID)
                .hasFieldOrPropertyWithValue("type", CommunityMemberType.MEMBER);
    }


}