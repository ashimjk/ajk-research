package io.ashimjk.prob1.handler;

import io.ashimjk.prob1.model.LetterOfGuarantee;

public abstract class BaseTransactionHandler implements TransactionHandler {

    protected TransactionDetails transactionDetails;
    protected LetterOfGuarantee letterOfGuarantee;

    @Override
    public void handleTransaction(TransactionDetails transactionDetails, LetterOfGuarantee letterOfGuarantee) {
        this.transactionDetails = transactionDetails;
        this.letterOfGuarantee = letterOfGuarantee;

        updateBalances(transactionDetails, letterOfGuarantee);
    }

    protected abstract void updateBalances(TransactionDetails transactionDetails, LetterOfGuarantee letterOfGuarantee);

}
