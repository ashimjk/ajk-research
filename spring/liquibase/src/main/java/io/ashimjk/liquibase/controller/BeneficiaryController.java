package io.ashimjk.liquibase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.liquibase.model.Beneficiary;
import io.ashimjk.liquibase.model.ContactPerson;
import io.ashimjk.liquibase.model.correspondentbank.CorrespondentBank;
import io.ashimjk.liquibase.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@RestController
@RequiredArgsConstructor
@RequestMapping("beneficiary")
public class BeneficiaryController {

    private final BeneficiaryRepository beneficiaryRepository;

    @GetMapping
    public ResponseEntity<List<Beneficiary>> getAll() {
        return ResponseEntity.ok(beneficiaryRepository.findAll());
    }

    @GetMapping("insert")
    public ResponseEntity<Beneficiary> insert() {
        Beneficiary beneficiary = beneficiaryRequest();
        beneficiary = this.beneficiaryRepository.save(beneficiary);

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("partialUpdate")
    public ResponseEntity<Beneficiary> partialUpdate() {
        Beneficiary beneficiary = this.beneficiaryRepository.findById(1L);
        beneficiary.updateFullName("ashim");

        this.beneficiaryRepository.save(beneficiary);

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("fullUpdate")
    public ResponseEntity<Beneficiary> fullUpdate() {
        Beneficiary beneficiary = this.beneficiaryRepository.findById(1L);
        beneficiary.updateFullName("ashim");

        List<ContactPerson> contactPersons = beneficiary.getContactPersons();
        contactPersons.get(0).updateFullName("ashim");

        CorrespondentBank correspondentBank = beneficiary.getCorrespondentBanks().get(0);
        correspondentBank.updateName("ashim");

        this.beneficiaryRepository.save(beneficiary);

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("delete")
    public ResponseEntity<Void> delete() {
        this.beneficiaryRepository.delete(1L);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SneakyThrows
    protected Beneficiary beneficiaryRequest() {
        String file = Objects.requireNonNull(BeneficiaryController.class.getClassLoader()
                .getResource("beneficiary.json"))
                .getFile();

        Path path = Paths.get(file);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(NON_EMPTY);
        mapper.setSerializationInclusion(NON_NULL);

        return mapper.readValue(path.toFile(), Beneficiary.class);
    }

}
