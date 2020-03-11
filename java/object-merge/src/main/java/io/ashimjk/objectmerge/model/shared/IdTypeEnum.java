package io.ashimjk.objectmerge.model.shared;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.ashimjk.objectmerge.model.shared.IdTypeEnum.IdTypeConstants.*;

public enum IdTypeEnum {

    INDIVIDUAL(ID_CARD, CITIZENSHIP_CERTIFICATE, PASSPORT, LICENSE),
    CORPORATE(MINISTRY_OF_INDUSTRY_CERTIFICATE, REGISTRATION_CERTIFICATE);

    private final List<String> types;

    IdTypeEnum(String... types) {
        this.types = Arrays.asList(types);
    }

    public static List<String> getIdTypes() {
        return Arrays.stream(values())
                .map(idType -> idType.types)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static List<String> getByIdType(String beneficiaryType) {
        return Arrays.stream(values())
                .filter(bType -> bType.name().equals(beneficiaryType))
                .findFirst()
                .map(idTypes -> idTypes.types)
                .orElseGet(Collections::emptyList);
    }

    public static class IdTypeConstants {
        public static final String ID_CARD = "ID Card";
        public static final String CITIZENSHIP_CERTIFICATE = "Citizenship Certificate";
        public static final String PASSPORT = "Passport";
        public static final String LICENSE = "License";
        public static final String REGISTRATION_CERTIFICATE = "Registration Certificate";
        public static final String MINISTRY_OF_INDUSTRY_CERTIFICATE = "Ministry Of Industry Certificate";

    }

}
