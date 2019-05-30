package io.ashimjk.tddworkout.lgdraft;

import java.util.Optional;

public interface LgDraftRepository {

    Optional<LgDraft> getLgDraftByRef(String lgDraft);

}
