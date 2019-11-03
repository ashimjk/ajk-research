package io.ashimjk.genericimpl.domain.authorizedsignature;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class DelegatedPerson implements Serializable {

    private static final long serialVersionUID = 7923179030032461293L;

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

    public static DelegatedPerson create(String fullName, String nationalNumber, String title, String signature,
                                         String document, List<Service> services) {

        DomainAsserts.isNotBlank(fullName, FULL_NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(nationalNumber, NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(document, DOCUMENT_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(signature, AUTHORIZED_SIGNATURE_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.nonNullList(services, SERVICE_LIST_SHOULD_NOT_BE_EMPTY);

        DelegatedPerson person = new DelegatedPerson();

        person.fullName = fullName;
        person.title = title;
        person.signature = signature;
        person.document = document;
        person.nationalNumber = nationalNumber;
        person.services.addAll(services);

        return person;
    }
}
