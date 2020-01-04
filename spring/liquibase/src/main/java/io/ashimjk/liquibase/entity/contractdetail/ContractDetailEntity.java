package io.ashimjk.liquibase.entity.contractdetail;

import io.ashimjk.liquibase.model.contractdetail.TermsOfPayment;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Embeddable
public class ContractDetailEntity implements Serializable {

    private static final long serialVersionUID = -7829971644762820963L;

    private Integer number;
    private String detail;
    private String amount;
    private BigDecimal balance;
    private BigDecimal feesAndCharges;

    @Column(name = "contract_from")
    private LocalDate from;

    @Column(name = "contract_to")
    private LocalDate to;

    @Enumerated(EnumType.STRING)
    private TermsOfPayment termsOfPayment;

}
