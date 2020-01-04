package io.ashimjk.liquibase.entity.correspondentbank;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 4988610475698346173L;

    private String iban;
    private String currency;
    private String accountNumber;
    private String accountAlias;

}
