package io.ashimjk.liquibase.model.contractdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ContractDetail implements Serializable {

    private static final long serialVersionUID = -8253516559316248152L;

    private Integer number;
    private String from;
    private String to;
    private String detail;
    private String amount;
    private String balance;
    private String feesAndCharges;
    private TermsOfPayment termsOfPayment;

}
