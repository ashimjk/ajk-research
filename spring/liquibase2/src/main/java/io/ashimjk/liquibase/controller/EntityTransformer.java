package io.ashimjk.liquibase.controller;

import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import io.ashimjk.liquibase.entity.CorrespondentBankEntity;
import io.ashimjk.liquibase.model.Beneficiary;
import io.ashimjk.liquibase.model.BeneficiaryFactory;
import io.ashimjk.liquibase.model.CorrespondentBank;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EntityTransformer {

    private static CorrespondentBank correspondentBank(CorrespondentBankEntity entity) {
        return new CorrespondentBank(
                entity.getName()
        );
    }

    private static <T, R> List<R> safeList(List<T> input, Function<T, R> transformerFunc) {
        return Optional.ofNullable(input).orElseGet(Collections::emptyList)
                .stream()
                .map(transformerFunc)
                .collect(Collectors.toList());
    }

    private static <T, R> List<R> safeSet(Set<T> input, Function<T, R> transformerFunc) {
        return Optional.ofNullable(input).orElseGet(Collections::emptySet)
                .stream()
                .map(transformerFunc)
                .collect(Collectors.toList());
    }

    public Beneficiary toBeneficiary(BeneficiaryEntity entity) {
        if (entity == null) {
            return null;
        }

        return BeneficiaryFactory.beneficiary(
                entity.getId(),
                entity.getFullName(),
                safeList(entity.getCorrespondentBanks(), EntityTransformer::correspondentBank)
        );
    }

}
