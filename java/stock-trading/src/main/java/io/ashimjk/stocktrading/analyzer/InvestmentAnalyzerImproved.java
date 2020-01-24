package io.ashimjk.stocktrading.analyzer;

import io.ashimjk.stocktrading.trader.Trader;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class InvestmentAnalyzerImproved implements Analyzer {

    private Trader stockTrader;
    private PriorityQueue<InvestmentQuery> queries = new PriorityQueue<>();
    private Map<String, Integer> stockCaching = new HashMap<>();
    private Random random = new Random(29);

    public InvestmentAnalyzerImproved(Trader stockTrader) {
        this.stockTrader = stockTrader;
    }

    @Override
    public void handleQuery(InvestmentQuery query) {
        this.queries.add(query);
    }

    @Override
    public void analyzeQueries() {
        while (this.queries.size() > 0) {
            int rating;
            InvestmentQuery query = queries.remove();

            if (stockCaching.containsKey(query.getStockId())) {
                rating = stockCaching.get(query.getStockId());
            } else {
                rating = calculateRating(query.getStockId());
                stockCaching.put(query.getStockId(), rating);
            }

            // Let's say that a rating of 80 triggers a stock trade
            if (rating > 80) {
                stockTrader.enqueueStockForTrading(query);
            }
        }
    }

    private int calculateRating(String stockID) {
        // Simulate some computation time that might involve
        // fetching data from various external data sources:
        // Thread.Sleep(100);

        // Simulate some calculation result:
        return random.nextInt(100);
    }

}
