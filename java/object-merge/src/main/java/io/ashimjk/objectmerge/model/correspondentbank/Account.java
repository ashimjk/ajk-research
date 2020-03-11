package io.ashimjk.objectmerge.model.correspondentbank;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {

    private static final long serialVersionUID = -8221757765548638307L;

    private String reference;
    private String iban;
    private String currency;
    private String accountNumber;
    private String accountAlias;

}
