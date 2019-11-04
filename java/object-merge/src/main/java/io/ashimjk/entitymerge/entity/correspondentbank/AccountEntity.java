package io.ashimjk.entitymerge.entity.correspondentbank;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
class AccountEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String iban;
    private String currencyOfIban;

}
