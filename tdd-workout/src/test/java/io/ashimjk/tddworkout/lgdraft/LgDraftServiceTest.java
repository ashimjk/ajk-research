package io.ashimjk.tddworkout.lgdraft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LgDraftServiceTest {

    private static final String LG_DRAFT_REF = "123";

    @Mock
    private LgDraftRepository lgDraftRepository;

    @InjectMocks
    private LgDraftService lgDraftService;

    @Test
    public void getLgDraft_ShouldReturnLgDraft() {
        when(lgDraftRepository.getLgDraftByRef(LG_DRAFT_REF)).thenReturn(Optional.of(new LgDraft(LG_DRAFT_REF, "lgdraft")));

        LgDraft lgDraft = lgDraftService.getLgDraft(LG_DRAFT_REF);

        assertThat(lgDraft.getReference()).isEqualTo(LG_DRAFT_REF);
        assertThat(lgDraft.getData()).isEqualTo("lgdraft");
    }

    @Test(expected = LgDraftNotFound.class)
    public void getLgDraft_ShouldThrowException() {
        when(lgDraftRepository.getLgDraftByRef(LG_DRAFT_REF)).thenReturn(Optional.empty());

        lgDraftService.getLgDraft(LG_DRAFT_REF);
    }

}