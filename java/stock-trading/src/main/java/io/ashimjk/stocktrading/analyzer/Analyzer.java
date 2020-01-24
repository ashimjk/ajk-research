package io.ashimjk.stocktrading.analyzer;

public interface Analyzer {

    void handleQuery(InvestmentQuery query);

    void analyzeQueries();

}
