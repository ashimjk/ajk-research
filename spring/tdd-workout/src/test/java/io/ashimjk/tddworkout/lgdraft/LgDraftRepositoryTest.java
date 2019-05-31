package io.ashimjk.tddworkout.lgdraft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LgDraftRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LgDraftRepository lgDraftRepository;

    @Test
    public void findByReference_ShouldReturnLGDraft() {
        // arrange
        LgDraft lgDraft = new LgDraft("124", "lgdraft");
        entityManager.persistFlushFind(lgDraft);

        // act
        Optional<LgDraft> dbLgDraft = lgDraftRepository.getLgDraftByReference("124");

        // assert
        assertThat(dbLgDraft.isPresent()).isTrue();
        assertThat(dbLgDraft.get().getReference()).isEqualTo(lgDraft.getReference());
        assertThat(dbLgDraft.get().getData()).isEqualTo(lgDraft.getData());

    }

}