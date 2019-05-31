package io.ashimjk.tddworkout.lgdraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LgDraftRepository extends JpaRepository<LgDraft, String> {

    Optional<LgDraft> getLgDraftByReference(String reference);

}
