package io.ashimjk.liquibase.model;

import lombok.Data;

@Data
public class ShareHolder {

    private Long id;
    private String name;
    private CorporateBeneficiary corporateBeneficiary;

}
