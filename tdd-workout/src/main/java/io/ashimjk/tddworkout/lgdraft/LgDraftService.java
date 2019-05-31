package io.ashimjk.tddworkout.lgdraft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class LgDraftService {

    private final LgDraftRepository lgDraftRepository;

    LgDraft getLgDraft(String reference) {
        Optional<LgDraft> lgDraftByRef = lgDraftRepository.getLgDraftByReference(reference);

        return lgDraftByRef.orElseThrow(LgDraftNotFound::new);
    }

}
