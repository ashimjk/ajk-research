package io.ashimjk.entitymerge.domain.correspondentbank;

import lombok.Data;

import java.io.Serializable;

@Data
class Account implements Serializable {

    private static final long serialVersionUID = -520883889974609909L;

    private String iban;
    private String currencyOfIban;

}
