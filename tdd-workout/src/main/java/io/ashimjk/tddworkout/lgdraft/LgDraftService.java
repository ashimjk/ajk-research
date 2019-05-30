package io.ashimjk.tddworkout.lgdraft;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class LgDraftService {

    private final LgDraftRepository lgDraftRepository;

    LgDraft getLgDraft(String reference) {
        Optional<LgDraft> lgDraftByRef = lgDraftRepository.getLgDraftByRef(reference);

        return lgDraftByRef.orElseThrow(LgDraftNotFound::new);
    }

}
