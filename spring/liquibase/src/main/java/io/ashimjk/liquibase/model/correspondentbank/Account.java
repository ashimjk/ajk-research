package io.ashimjk.liquibase.model.correspondentbank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Account implements Serializable {

    private static final long serialVersionUID = -8221757765548638307L;

    private String iban;
    private String currency;
    private String accountNumber;
    private String accountAlias;

}
