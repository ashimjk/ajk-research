package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends BaseEntity {

    private String iban;
    private String currencyOfIban;

}
