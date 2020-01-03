package io.ashimjk.liquibase.model;

import lombok.Data;

import java.util.Set;

@Data
public class CorporateBeneficiary {

    private Long id;
    private Set<ContactPerson> contactPersons;

}
