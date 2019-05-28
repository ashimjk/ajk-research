package io.ashimjk.prob1.handler;

public class TransactionDetails {

    private TransactionType label;

    public TransactionType getLabel() {
        return label;
    }

    public enum TransactionType {
        CLAIMS_PAID,
        FEES_AND_CHARGES,
        CORRESPONDENT_BANK_CHARGES,
        BENEFICIARY_CHARGES,
        COLLATERAL
    }

}
