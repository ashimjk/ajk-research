package io.ashimjk.entitymerge.request.correspondentbank;

import lombok.Data;

import java.io.Serializable;

@Data
public
class AccountRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String iban;
    private String currencyOfIban;

}

