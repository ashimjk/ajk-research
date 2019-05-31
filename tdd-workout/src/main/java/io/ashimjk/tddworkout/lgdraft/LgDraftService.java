package io.ashimjk.tddworkout.lgdraft;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LgDraftService {

    private final LgDraftRepository lgDraftRepository;

    @Cacheable("lgDraft")
    public LgDraft getLgDraft(String reference) {
        Optional<LgDraft> lgDraftByRef = lgDraftRepository.getLgDraftByReference(reference);

        return lgDraftByRef.orElseThrow(LgDraftNotFound::new);
    }

}
