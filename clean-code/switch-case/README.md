# Switch Case Problem

## Problem 1
There are couple of problem with this approach.
Lets identify it by following `FinancialAdviceUseCase' class.

## First
- `process()` method contains list of transaction details.
- abstract each transaction detail from list and delegate request to `handleTransaction()` method.

## Second
- now handleTransaction() gets
    - TransactionHandler from TransactionFactory by passing two parameters.
        - TransactionType
        - FinancialAdviceRepository
