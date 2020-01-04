package io.ashimjk.liquibase.repository;

import io.ashimjk.liquibase.controller.EntityMapper;
import io.ashimjk.liquibase.controller.EntityTransformer;
import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import io.ashimjk.liquibase.model.Beneficiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BeneficiaryRepository {

    private final EntityMapper entityMapper;
    private final EntityTransformer entityTransformer;
    private final JpaDataBeneficiaryRepository repository;

    public List<Beneficiary> findAll() {
        return repository.findAll().stream()
                .map(entityTransformer::toBeneficiary)
                .collect(Collectors.toList());
    }

    public Beneficiary findById(long id) {
        BeneficiaryEntity entity = repository.findById(id).orElseGet(BeneficiaryEntity::new);
        return entityTransformer.toBeneficiary(entity);
    }

    public Beneficiary save(Beneficiary beneficiary) {
        BeneficiaryEntity entity = entityMapper.toEntity(beneficiary);
        return entityTransformer.toBeneficiary(repository.save(entity));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

}
