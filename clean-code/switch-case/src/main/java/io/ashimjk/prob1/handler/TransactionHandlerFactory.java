package io.ashimjk.prob1.handler;

import io.ashimjk.prob1.handler.sub.*;
import io.ashimjk.prob1.repo.FinancialAdviceRepository;

public class TransactionHandlerFactory {

    private static final TransactionHandlerFactory INSTANCE = new TransactionHandlerFactory();

    private TransactionHandlerFactory() {
    }

    public static TransactionHandlerFactory getFactory() {
        return INSTANCE;
    }

    public TransactionHandler getTransactionHandler(TransactionDetails.TransactionType transactionType, FinancialAdviceRepository financialAdviceRepository) {

        switch (transactionType) {
            case CLAIMS_PAID:
                return new ClaimsPaidHandler(financialAdviceRepository);
            case FEES_AND_CHARGES:
                return new FeesAndChargesHandler();
            case CORRESPONDENT_BANK_CHARGES:
                return new CorrespondentHandler();
            case BENEFICIARY_CHARGES:
                return new BeneficiaryHandler();
            case COLLATERAL:
                return new CollateralHandler();
            default:
                throw new IllegalArgumentException("unsupported transaction type: " + transactionType);
        }
    }

}
