package io.ashimjk.prob1.handler;

import io.ashimjk.prob1.model.LetterOfGuarantee;

public interface TransactionHandler {

    void handleTransaction(TransactionDetails transactionDetails, LetterOfGuarantee letterOfGuarantee);

}
