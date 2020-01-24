package io.ashimjk.stocktrading.trader;

import io.ashimjk.stocktrading.analyzer.InvestmentQuery;

public interface Trader {

    void enqueueStockForTrading(InvestmentQuery query);

    void handleTradings();

}
