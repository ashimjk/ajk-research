package io.ashimjk.stocktrading.trader;

import io.ashimjk.stocktrading.analyzer.InvestmentQuery;

import java.util.LinkedList;

public class StockTraderImproved implements Trader {

    LinkedList<InvestmentQuery> stocksToTrade = new LinkedList<>();

    @Override
    public void enqueueStockForTrading(InvestmentQuery query) {
        stocksToTrade.add(query);
    }

    @Override
    public void handleTradings() {
        System.out.print(String.format(" [{%d} stocks] ", stocksToTrade.size()));

        while (stocksToTrade.size() > 0) {
            InvestmentQuery query = stocksToTrade.getFirst();

            // As this is simulation of a real service that consumes queries as they arrive,
            // remember to remove the query from the list when processed:

            stocksToTrade.removeFirst();

            // Simulate stock trade:
            // Thread.Sleep(100);
        }
    }

}
