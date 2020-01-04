package io.ashimjk.liquibase.model.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class IdType implements Serializable {

    public static final String NATIONAL_NUMBER = "National Number";
    public static final String PASSPORT_NUMBER = "Passport Number";
    public static final String LICENSE_NUMBER = "License Number";
    public static final String REGISTRATION_CERTIFICATE = "Registration Certificate";
    public static final String MINISTRY_OF_INDUSTRY_CERTIFICATE = "Ministry Of Industry Certificate";
    private static final long serialVersionUID = 4548283707786396501L;

    private String name;
    private String number;
    private String document;
    private String expiryDate;

}
