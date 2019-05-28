package io.ashimjk.prob1.handler.sub;

import io.ashimjk.prob1.handler.BaseTransactionHandler;
import io.ashimjk.prob1.handler.TransactionDetails;
import io.ashimjk.prob1.model.LetterOfGuarantee;
import io.ashimjk.prob1.usecase.BalanceUpdateUseCase;

public class CorrespondentHandler extends BaseTransactionHandler {

    private final BalanceUpdateUseCase balanceUpdateUseCase = new BalanceUpdateUseCase();

    @Override
    protected void updateBalances(TransactionDetails transactionDetails, LetterOfGuarantee letterOfGuarantee) {
        this.balanceUpdateUseCase.updateBalance(transactionDetails, letterOfGuarantee);
    }

}
