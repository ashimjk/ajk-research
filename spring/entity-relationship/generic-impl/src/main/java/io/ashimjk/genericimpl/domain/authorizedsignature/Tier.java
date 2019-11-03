package io.ashimjk.genericimpl.domain.authorizedsignature;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;

@Getter
@Embeddable
@EqualsAndHashCode
public class Tier implements Serializable {

    private static final long serialVersionUID = 6426747227225855251L;

    @Column(name = "tier_from")
    private BigDecimal from;

    @Column(name = "tier_to")
    private BigDecimal to;

    public static Tier create(String from, String to) {
        DomainAsserts.isNotBlank(from, TIER_FROM_MUST_NOT_BE_EMPTY);
        DomainAsserts.isNotBlank(to, TIER_TO_MUST_NOT_BE_EMPTY);
        DomainAsserts.isBigDecimal(from, TIER_FROM_MUST_BE_VALID);
        DomainAsserts.isBigDecimal(to, TIER_TO_MUST_BE_VALID);

        Tier tier = new Tier();
        tier.from = new BigDecimal(from);
        tier.to = new BigDecimal(to);
        return tier;
    }
}
