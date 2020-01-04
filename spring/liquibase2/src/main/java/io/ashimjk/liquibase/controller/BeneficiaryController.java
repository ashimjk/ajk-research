package io.ashimjk.liquibase.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import io.ashimjk.liquibase.model.Beneficiary;
import io.ashimjk.liquibase.model.CorrespondentBank;
import io.ashimjk.liquibase.repository.JpaDataBeneficiaryRepository;
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
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@RestController
@RequiredArgsConstructor
@RequestMapping("beneficiary")
public class BeneficiaryController {

    private final EntityMapper entityMapper;
    private final EntityTransformer entityTransformer;
    private final JpaDataBeneficiaryRepository repository;

    @GetMapping
    public ResponseEntity<List<Beneficiary>> getAll() {
        List<Beneficiary> beneficiaries = repository.findAll().stream()
                .map(entityTransformer::toBeneficiary)
                .collect(Collectors.toList());

        return ResponseEntity.ok(beneficiaries);
    }

    @GetMapping("insert")
    public ResponseEntity<Beneficiary> insert() {
        Beneficiary beneficiary = beneficiaryRequest();
        beneficiary = entityTransformer.toBeneficiary(repository.save(entityMapper.toEntity(beneficiary)));

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("partialUpdate")
    public ResponseEntity<Beneficiary> partialUpdate() {
        Beneficiary beneficiary = entityTransformer.toBeneficiary(this.repository.findById(1L).orElseGet(BeneficiaryEntity::new));
        beneficiary.updateFullName("ashim");

        beneficiary = entityTransformer.toBeneficiary(repository.save(entityMapper.toEntity(beneficiary)));

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("fullUpdate")
    public ResponseEntity<Beneficiary> fullUpdate() {
        Beneficiary beneficiary = entityTransformer.toBeneficiary(this.repository.findById(1L).orElseGet(BeneficiaryEntity::new));
        beneficiary.updateFullName("ashim");

        CorrespondentBank correspondentBank = beneficiary.getCorrespondentBanks().get(0);
        correspondentBank.updateName("ashim");

        beneficiary = entityTransformer.toBeneficiary(repository.save(entityMapper.toEntity(beneficiary)));

        return ResponseEntity.ok(beneficiary);
    }

    @GetMapping("delete")
    public ResponseEntity<Void> delete() {
        this.repository.deleteById(1L);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SneakyThrows
    protected Beneficiary beneficiaryRequest() {
        String file = Objects.requireNonNull(BeneficiaryController.class.getClassLoader()
                .getResource("beneficiary.json"))
                .getFile();

        Path path = Paths.get(file);

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(NON_EMPTY);
        mapper.setSerializationInclusion(NON_NULL);

        return mapper.readValue(path.toFile(), Beneficiary.class);
    }

}
