package io.ashimjk.genericimpl.domain.deal;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.DEAL_TYPE_SHOULD_NOT_BE_EMPTY;
import static io.ashimjk.genericimpl.exception.DomainViolationConstants.SERVICE_LIST_SHOULD_NOT_BE_EMPTY;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class TypeOfDealing implements Serializable {

    private static final long serialVersionUID = 8636872125309310838L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @ElementCollection
    private List<String> services = new ArrayList<>();

    public static TypeOfDealing create(DealType dealType, List<String> services) {

        DomainAsserts.isNull(dealType, DEAL_TYPE_SHOULD_NOT_BE_EMPTY);

        if (DealType.ONLY_CORRESPONDENCE != dealType) {
            DomainAsserts.nonNullList(services, SERVICE_LIST_SHOULD_NOT_BE_EMPTY);
        }

        TypeOfDealing typeOfDealing = new TypeOfDealing();
        typeOfDealing.dealType = dealType;
        typeOfDealing.services.addAll(services);
        return typeOfDealing;
    }
}
