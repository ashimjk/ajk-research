package io.ashimjk.genericimpl.controller;

import io.ashimjk.genericimpl.domain.Beneficiary;
import io.ashimjk.genericimpl.domain.IdType;
import io.ashimjk.genericimpl.repository.BeneficiaryRepository;
import io.ashimjk.genericimpl.support.ObjectMerger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.domain.ExampleMatcher.matching;

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

    @GetMapping("/filter")
    public ResponseEntity<List<Beneficiary>> filter() {
        IdType idType = new IdType();
        idType.setExpiryDate(LocalDate.now());

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.addIdType(idType);

        Example<Beneficiary> ex = Example.of(beneficiary, matching().withIgnoreNullValues().withStringMatcher(CONTAINING));

        List<Beneficiary> all = repository.findAll(ex);

        return ResponseEntity.ok(all);
    }
}
