package io.ashimjk.genericimpl.domain.contractdetail;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;

@Getter
@Embeddable
@EqualsAndHashCode
public class ContractDetail implements Serializable {

    private static final long serialVersionUID = -9172540913254519810L;

    private Integer number;

    @Column(name = "contract_from")
    private LocalDate from;

    @Column(name = "contract_to")
    private LocalDate to;

    private String details;

    private String amount;

    private BigDecimal balance;

    private BigDecimal feesAndCharges;

    @Enumerated(EnumType.STRING)
    private TermsOfPayment termsOfPayment;

    public static ContractDetail create(Integer number,
                                        LocalDate from,
                                        LocalDate to,
                                        String details,
                                        String amount,
                                        String balance,
                                        String feesAndCharges,
                                        TermsOfPayment termsOfPayment) {

        DomainAsserts.isNull(number, NUMBER_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNull(from, FROM_DATE_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNull(to, TO_DATE_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(details, DETAILS_SHOULD_NOT_BE_BLANK);
        DomainAsserts.isNotBlank(amount, AMOUNT_SHOULD_NOT_BE_BLANK);
        DomainAsserts.isNotBlank(balance, BALANCE_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(feesAndCharges, FEES_AND_CHARGES_SHOULD_NOT_BE_NULL);
        DomainAsserts.isBigDecimal(balance, BALANCE_SHOULD_BE_VALID_NUMBER);
        DomainAsserts.isBigDecimal(feesAndCharges, FEES_AND_CHARGES_SHOULD_BE_VALID_NUMBER);
        DomainAsserts.isNull(termsOfPayment, TERMS_OF_PAYMENT_SHOULD_NOT_BE_NULL);

        ContractDetail contractDetail = new ContractDetail();

        contractDetail.number = number;
        contractDetail.from = from;
        contractDetail.to = to;
        contractDetail.details = details;
        contractDetail.amount = amount;
        contractDetail.balance = new BigDecimal(balance);
        contractDetail.feesAndCharges = new BigDecimal(feesAndCharges);
        contractDetail.termsOfPayment = termsOfPayment;

        return contractDetail;
    }
}
