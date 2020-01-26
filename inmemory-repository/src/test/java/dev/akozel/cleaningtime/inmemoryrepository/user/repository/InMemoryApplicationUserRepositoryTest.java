package dev.akozel.cleaningtime.inmemoryrepository.user.repository;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.inmemoryrepository.helper.IdGenerationHelper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class InMemoryApplicationUserRepositoryTest {

    private static final Integer NEXT_ID = 432534;

    @Mock
    private IdGenerationHelper idGenerationHelper;
    @InjectMocks
    private InMemoryApplicationUserRepository sut;

    @Test
    public void should_set_id_for_saved_community() {
        //given

        ApplicationUser validUser = ApplicationUser.builder()
                .build();
        given(idGenerationHelper.generateId())
                .willReturn(NEXT_ID);

        //when
        sut.save(validUser);

        //then
        Assertions.assertThat(validUser.getId())
                .isEqualTo(validUser.getId());
    }

    @Test
    public void should_return_item_id_when_item_saved() {
        //given
        ApplicationUser validUser = ApplicationUser.builder()
                .build();
        given(idGenerationHelper.generateId())
                .willReturn(NEXT_ID);

        //when
        Integer actual = sut.save(validUser);

        //then
        Assertions.assertThat(actual)
                .isEqualTo(NEXT_ID);
    }

}