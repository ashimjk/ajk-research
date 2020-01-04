package io.ashimjk.liquibase.model;

import lombok.Data;

import java.util.List;

@Data
public class CorporateBeneficiary {

    private Long id;
    private List<ContactPerson> contactPersons;

}
