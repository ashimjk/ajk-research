package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class ContractDetail implements Serializable {

    private Integer number;

    private LocalDate confrom;

    private LocalDate conto;

    private String details;

    private String amount;

    private BigDecimal balance;

    private BigDecimal feesAndCharges;

    @Embedded
    @Enumerated(EnumType.STRING)
    private TermsOfPayment termsOfPayment;

}
