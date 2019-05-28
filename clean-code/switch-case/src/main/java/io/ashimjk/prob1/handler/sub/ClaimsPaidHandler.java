package io.ashimjk.prob1.handler.sub;

import io.ashimjk.prob1.handler.BaseTransactionHandler;
import io.ashimjk.prob1.handler.TransactionDetails;
import io.ashimjk.prob1.model.LetterOfGuarantee;
import io.ashimjk.prob1.repo.FinancialAdviceRepository;

public class ClaimsPaidHandler extends BaseTransactionHandler {

    private FinancialAdviceRepository financialAdviceRepository;

    ClaimsPaidHandler(FinancialAdviceRepository financialAdviceRepository) {
        this.financialAdviceRepository = financialAdviceRepository;
    }

    @Override
    protected void updateBalances(TransactionDetails txn, LetterOfGuarantee letterOfGuarantee) {
        // do some business logic
        // can use financialAdviceRepository for further logic
    }

}
