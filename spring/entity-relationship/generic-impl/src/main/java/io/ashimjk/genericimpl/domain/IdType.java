package io.ashimjk.genericimpl.domain;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class IdType implements Serializable {

    private static final long serialVersionUID = 8453836651385901258L;

    private String name;

    private String number;

    private String document;

    private LocalDate expiryDate;

    public static IdType create(String name, String number, String document, LocalDate expiryDate) {

        DomainAsserts.isNotBlank(name, ID_TYPE_NAME_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(number, ID_TYPE_NUMBER_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(document, ID_TYPE_DOCUMENT_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNull(expiryDate, EXPIRY_DATE_SHOULD_NOT_BE_NULL);

        IdType idType = new IdType();

        idType.document = document;
        idType.number = number;
        idType.name = name;
        idType.expiryDate = expiryDate;

        return idType;
    }
}
