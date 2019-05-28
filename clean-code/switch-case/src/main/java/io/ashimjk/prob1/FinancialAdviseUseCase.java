package io.ashimjk.prob1;

import io.ashimjk.prob1.handler.TransactionDetails;
import io.ashimjk.prob1.handler.TransactionHandlerFactory;
import io.ashimjk.prob1.model.LetterOfGuarantee;
import io.ashimjk.prob1.repo.FinancialAdviceRepository;

import java.util.List;

public class FinancialAdviseUseCase {

    private FinancialAdviceRepository financialAdviceRepository;

    public void process(FinancialAdviceMessage message) {

        LetterOfGuarantee letterOfGuarantee = new LetterOfGuarantee();

        message.getTransactionDetails()
                .forEach(transactionDetails -> handleTransaction(letterOfGuarantee, transactionDetails));
    }

    private void handleTransaction(LetterOfGuarantee letterOfGuarantee, TransactionDetails transactionDetails) {
        TransactionHandlerFactory.getFactory()
                .getTransactionHandler(transactionDetails.getLabel(), financialAdviceRepository)
                .handleTransaction(transactionDetails, letterOfGuarantee);
    }

    static class FinancialAdviceMessage {

        private List<TransactionDetails> transactionDetails;

        List<TransactionDetails> getTransactionDetails() {
            return transactionDetails;
        }

    }

}
