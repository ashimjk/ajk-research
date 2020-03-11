package io.ashimjk.objectmerge.model.shared;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum BeneficiaryType {
    CORPORATE, INDIVIDUAL;

    public static BeneficiaryType of(String beneficiaryType) {
        boolean contains = Arrays.stream(BeneficiaryType.values())
                .map(BeneficiaryType::name)
                .collect(Collectors.toList())
                .contains(beneficiaryType);

        return contains ? valueOf(beneficiaryType) : null;
    }
}
