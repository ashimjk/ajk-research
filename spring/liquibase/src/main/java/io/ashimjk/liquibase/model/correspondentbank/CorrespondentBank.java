package io.ashimjk.liquibase.model.correspondentbank;

import io.ashimjk.liquibase.model.shared.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CorrespondentBank implements Serializable {

    private static final long serialVersionUID = 213942640908917631L;

    private Long id;
    private String name;
    private String swiftCode;
    private Address address;
    private List<String> services;
    private List<Account> accounts;

    public void updateName(String name) {
        this.name = name;
    }

}
