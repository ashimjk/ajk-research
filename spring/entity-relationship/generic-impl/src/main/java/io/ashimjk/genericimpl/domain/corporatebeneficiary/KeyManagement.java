package io.ashimjk.genericimpl.domain.corporatebeneficiary;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;

@Getter
@Embeddable
@EqualsAndHashCode
public class KeyManagement implements Serializable {

    private static final long serialVersionUID = -841930811965658132L;

    private String fullName;

    private String title;

    private String nationalNumber;

    public static KeyManagement create(String fullName, String title, String nationalNumber) {

        DomainAsserts.isNotBlank(fullName, FULL_NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(title, TITLE_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(nationalNumber, NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY);

        KeyManagement keyManagement = new KeyManagement();
        keyManagement.title = title;
        keyManagement.nationalNumber = nationalNumber;
        keyManagement.fullName = fullName;

        return keyManagement;
    }

}
