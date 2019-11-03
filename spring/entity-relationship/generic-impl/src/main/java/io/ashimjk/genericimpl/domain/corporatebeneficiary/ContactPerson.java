package io.ashimjk.genericimpl.domain.corporatebeneficiary;

import io.ashimjk.genericimpl.domain.Address;
import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class ContactPerson implements Serializable {

    private static final long serialVersionUID = -8678799163275094142L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String title;

    private String fullName;

    private String email;

    private String phoneNo;

    @Embedded
    private Address address;

    private String nationalNumber;

    @ElementCollection
    private List<String> services = new ArrayList<>();

    public static ContactPerson create(List<String> services,
                                       Address address,
                                       String email,
                                       String fullName,
                                       String nationalNumber,
                                       String phoneNo,
                                       String title) {

        DomainAsserts.nonNullList(services, SERVICE_LIST_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(email, EMAIL_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(fullName, FULL_NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(nationalNumber, NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(phoneNo, PHONE_NO_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(title, TITLE_SHOULD_NOT_BE_NULL);

        ContactPerson person = new ContactPerson();

        person.email = email;
        person.services.addAll(services);
        person.address = address;
        person.title = title;
        person.fullName = fullName;
        person.nationalNumber = nationalNumber;
        person.phoneNo = phoneNo;

        return person;
    }
}
