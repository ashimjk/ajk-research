package io.ashimjk.genericimpl.domain.corporatebeneficiary;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.FULL_NAME_SHOULD_NOT_BE_EMPTY;
import static io.ashimjk.genericimpl.exception.DomainViolationConstants.NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY;

@Getter
@Embeddable
@EqualsAndHashCode
public class ShareHolder implements Serializable {

    private static final long serialVersionUID = 6347815036535248849L;

    private String fullName;

    private String nationalId;

    public static ShareHolder create(String fullName, String nationalId) {

        DomainAsserts.isNotBlank(fullName, FULL_NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(nationalId, NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY);

        ShareHolder shareHolder = new ShareHolder();

        shareHolder.fullName = fullName;
        shareHolder.nationalId = nationalId;

        return shareHolder;

    }
}
