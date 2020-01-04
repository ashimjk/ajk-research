package io.ashimjk.liquibase.model;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BeneficiaryFactory {

    public static Beneficiary beneficiary(
            Long id,
            String fullName,
            List<CorrespondentBank> correspondentBanks
    ) {
        return Beneficiary.builder()
                .id(id)
                .fullName(fullName)
                .correspondentBanks(correspondentBanks)
                .build();
    }

}
