package io.ashimjk.entityrelationship.controller;

import io.ashimjk.entityrelationship.domain.Beneficiary;
import io.ashimjk.entityrelationship.repository.BeneficiaryRepository;
import io.ashimjk.entityrelationship.support.ObjectMerger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryRepository repository;

    @PostMapping
    public ResponseEntity<Beneficiary> createBeneficiary(@RequestBody Beneficiary beneficiary) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(beneficiary));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiary> getBeneficiary(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiary> editBeneficiary(@PathVariable Long id, @RequestBody Beneficiary request) {

        Beneficiary beneficiary = repository.findById(id).get();

        Beneficiary merge = ObjectMerger.applyUpdates(beneficiary, request);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(merge));
    }
}
