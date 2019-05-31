package io.ashimjk.tddworkout;

import io.ashimjk.tddworkout.lgdraft.LgDraft;
import io.ashimjk.tddworkout.lgdraft.LgDraftRepository;
import io.ashimjk.tddworkout.lgdraft.LgDraftService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CachingTest {

    @MockBean
    private LgDraftRepository repository;

    @Autowired
    private LgDraftService lgDraftService;

    @Test
    public void getLgDraft_ShouldReturnLgDraftFromCaching() {
        LgDraft lgDraft = new LgDraft("123", "lg draft");
        when(repository.getLgDraftByReference(anyString())).thenReturn(Optional.of(lgDraft));

        lgDraftService.getLgDraft("123");
        LgDraft dbLgDraft = lgDraftService.getLgDraft("123");

        verify(repository, times(1)).getLgDraftByReference("123");

        assertThat(dbLgDraft.getReference()).isEqualTo("123");
        assertThat(dbLgDraft.getData()).isEqualTo("lg draft");
    }

}
