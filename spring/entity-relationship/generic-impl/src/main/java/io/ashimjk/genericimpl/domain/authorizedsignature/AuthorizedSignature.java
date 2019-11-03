package io.ashimjk.genericimpl.domain.authorizedsignature;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = "id")
public class AuthorizedSignature implements Serializable {

    private static final long serialVersionUID = 2056849173358942133L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String fullName;

    private String nationalNumber;

    private String title;

    private String signature;

    private String document;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();

    @OneToOne(cascade = ALL, orphanRemoval = true)
    private DelegatedPerson delegatedPerson;

    public static AuthorizedSignature create(String fullName,
                                             String nationalNumber,
                                             String title,
                                             String document,
                                             String signature,
                                             List<Service> services,
                                             DelegatedPerson person
    ) {

        DomainAsserts.isNotBlank(fullName, FULL_NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(nationalNumber, NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(document, DOCUMENT_SHOULD_NOT_BE_NULL);
        DomainAsserts.nonNullList(services, SERVICE_LIST_SHOULD_NOT_BE_EMPTY);

        AuthorizedSignature authorizedSignature = new AuthorizedSignature();

        authorizedSignature.fullName = fullName;
        authorizedSignature.nationalNumber = nationalNumber;
        authorizedSignature.title = title;
        authorizedSignature.document = document;
        authorizedSignature.signature = signature;
        authorizedSignature.services.addAll(services);
        authorizedSignature.delegatedPerson = person;

        return authorizedSignature;
    }

}
