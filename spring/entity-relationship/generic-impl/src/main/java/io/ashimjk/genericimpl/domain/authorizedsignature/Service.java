package io.ashimjk.genericimpl.domain.authorizedsignature;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.SERVICE_NAME_SHOULD_NOT_BE_BLANK;
import static io.ashimjk.genericimpl.exception.DomainViolationConstants.TIER_SHOULD_NOT_BE_NULL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class Service implements Serializable {

    private static final long serialVersionUID = -3191617581859324560L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    @Embedded
    private Tier tier;

    public static Service create(String name, Tier tier) {

        DomainAsserts.isNotBlank(name, SERVICE_NAME_SHOULD_NOT_BE_BLANK);
        DomainAsserts.isNull(tier, TIER_SHOULD_NOT_BE_NULL);

        Service service = new Service();
        service.name = name;
        service.tier = tier;
        return service;

    }

}
