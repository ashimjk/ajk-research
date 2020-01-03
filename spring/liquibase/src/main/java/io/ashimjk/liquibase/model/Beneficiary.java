package io.ashimjk.liquibase.model;

import lombok.Data;

@Data
public class Beneficiary {

    private Long id;
    private String name;
    private CorporateBeneficiary corporateBeneficiary;

}
