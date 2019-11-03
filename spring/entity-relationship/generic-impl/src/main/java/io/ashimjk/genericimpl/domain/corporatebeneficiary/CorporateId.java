package io.ashimjk.genericimpl.domain.corporatebeneficiary;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.CORPORATE_ID_SHOULD_NOT_BE_EMPTY;

@Getter
@Embeddable
@EqualsAndHashCode
public class CorporateId implements Serializable {

    private static final long serialVersionUID = -9084436349886730562L;

    @Column(name = "corpay_id")
    private String id;

    public static CorporateId create(String id) {
        DomainAsserts.isNotBlank(id, CORPORATE_ID_SHOULD_NOT_BE_EMPTY);

        CorporateId corporateId = new CorporateId();
        corporateId.id = id;

        return corporateId;
    }

}
