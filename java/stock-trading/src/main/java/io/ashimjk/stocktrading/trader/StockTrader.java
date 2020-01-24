package io.ashimjk.stocktrading.trader;

import io.ashimjk.stocktrading.analyzer.InvestmentQuery;

import java.util.ArrayList;
import java.util.List;

public class StockTrader implements Trader {

    List<InvestmentQuery> stocksToTrade = new ArrayList<>();

    @Override
    public void enqueueStockForTrading(InvestmentQuery query) {
        stocksToTrade.add(query);
    }

    @Override
    public void handleTradings() {
        System.out.print(String.format(" [{%d} stocks] ", stocksToTrade.size()));

        while (stocksToTrade.size() > 0) {
            InvestmentQuery query = stocksToTrade.get(0);

            // As this is simulation of a real service that consumes queries as they arrive,
            // remember to remove the query from the list when processed:

            stocksToTrade.remove(0);

            // Simulate stock trade:
            // Thread.Sleep(100);
        }
    }

}
