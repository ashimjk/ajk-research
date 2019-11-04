package io.ashimjk.entitymerge.entity.authorizedsignature;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
class TierEntity {

    @Column(name = "tier_from")
    private BigDecimal from;

    @Column(name = "tier_to")
    private BigDecimal to;

}
