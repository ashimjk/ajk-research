package io.ashimjk.entitymerge.domain.authorizedsignature;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
class Tier implements Serializable {

    private static final long serialVersionUID = 6426747227225855251L;

    @Column(name = "tier_from")
    private BigDecimal from;

    @Column(name = "tier_to")
    private BigDecimal to;

}
