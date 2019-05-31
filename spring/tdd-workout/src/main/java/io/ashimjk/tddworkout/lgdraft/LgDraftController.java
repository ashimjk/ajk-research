package io.ashimjk.tddworkout.lgdraft;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lg-drafts")
public class LgDraftController {

    private final LgDraftService lgDraftService;

    @GetMapping("/{reference}")
    public LgDraftResource getLgDraft(@PathVariable String reference) {
        LgDraft lgDraft = lgDraftService.getLgDraft(reference);

        return new LgDraftResource(lgDraft.getReference(), lgDraft.getData());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void lgDraftNotFound(LgDraftNotFound ex) {}

}
