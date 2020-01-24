package io.ashimjk.stocktrading.analyzer;

import io.ashimjk.stocktrading.trader.Trader;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class InvestmentAnalyzer implements Analyzer {

    private Trader stockTrader;
    private List<InvestmentQuery> queries = new ArrayList<>();
    private List<RatingCacheElement> stockCaching = new ArrayList<>();
    private Random random = new Random(29);

    public InvestmentAnalyzer(Trader stockTrader) {
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

            InvestmentQuery query = getFirstPriority(queries); // Get first-priority queries first

            Optional<RatingCacheElement> cacheElementOpt = stockCaching.stream()
                    .filter(x -> x.stockId.equals(query.getStockId()))
                    .findFirst();

            if (cacheElementOpt.isPresent())
                rating = cacheElementOpt.get().rating;
            else {
                rating = calculateRating(query.getStockId());
                stockCaching.add(new RatingCacheElement(query.getStockId(), rating));
            }

            // Let's say that a rating of 80 triggers a stock trade
            if (rating > 80) {
                stockTrader.enqueueStockForTrading(query);
            }
        }
    }

    private InvestmentQuery getFirstPriority(List<InvestmentQuery> queries) {
        InvestmentQuery minQuery = null;
        for (InvestmentQuery query : queries) {
            if (minQuery == null || query.compareTo(minQuery) < 0)
                minQuery = query;
        }
        queries.remove(minQuery);
        return minQuery;
    }

    private int calculateRating(String stockID) {
        // Simulate some computation time that might involve
        // fetching data from various external data sources:
        // Thread.Sleep(100);

        // Simulate some calculation result:
        return random.nextInt(100);
    }

    @Data
    @Builder
    static class RatingCacheElement {

        private String stockId;
        private int rating;

    }

}
