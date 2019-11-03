package io.ashimjk.genericimpl.domain.correspondentbank;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.CURRENCY_SHOULD_NOT_BE_NULL;
import static io.ashimjk.genericimpl.exception.DomainViolationConstants.IBAN_ID_SHOULD_NOT_BE_NULL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class Account implements Serializable {

    private static final long serialVersionUID = -520883889974609909L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String iban;

    private String currencyOfIban;

    public static Account create(String iban, String currencyOfIban) {
        DomainAsserts.isNotBlank(iban, IBAN_ID_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(currencyOfIban, CURRENCY_SHOULD_NOT_BE_NULL);

        Account account = new Account();
        account.iban = iban;
        account.currencyOfIban = currencyOfIban;
        return account;
    }
}
