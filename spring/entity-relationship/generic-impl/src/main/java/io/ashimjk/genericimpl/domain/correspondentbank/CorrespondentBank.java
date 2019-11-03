package io.ashimjk.genericimpl.domain.correspondentbank;

import io.ashimjk.genericimpl.domain.Address;
import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class CorrespondentBank implements Serializable {

    private static final long serialVersionUID = -3227813107196832875L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    private String bicCode;

    @Embedded
    private Address address;

    @ElementCollection
    private List<String> services = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "correspondent_bank_id")
    private Set<Account> accounts = new HashSet<>();

    public static CorrespondentBank create(String name, String bicCode, Address address,
                                           List<String> services, Set<Account> accounts) {

        DomainAsserts.isNotBlank(name, NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(bicCode, BIC_CODE_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNull(address, ADDRESS_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.nonNullList(services, SERVICE_LIST_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNullOrEmpty(accounts, ACCOUNT_SHOULD_NOT_BE_EMPTY);

        CorrespondentBank correspondentBank = new CorrespondentBank();

        correspondentBank.name = name;
        correspondentBank.bicCode = bicCode;
        correspondentBank.address = address;
        correspondentBank.services.addAll(services);
        correspondentBank.accounts.addAll(accounts);

        return correspondentBank;
    }
}
