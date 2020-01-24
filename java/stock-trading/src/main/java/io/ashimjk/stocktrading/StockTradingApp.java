package io.ashimjk.stocktrading;

import io.ashimjk.stocktrading.analyzer.Analyzer;
import io.ashimjk.stocktrading.analyzer.InvestmentAnalyzerImproved;
import io.ashimjk.stocktrading.analyzer.InvestmentQuery;
import io.ashimjk.stocktrading.trader.StockTraderImproved;
import io.ashimjk.stocktrading.trader.Trader;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class StockTradingApp {

    private static Random random = new Random();

    public static void main(String[] args) {
        Trader stockTrader = new StockTraderImproved();
        Analyzer investmentAnalyzer = new InvestmentAnalyzerImproved(stockTrader);

        // Simulate stock trading
        stockTradingWith200000Stocks(stockTrader);

        // Simulate queries
        initializeQueries(investmentAnalyzer);

        // Simulate handling of queries
        handleQueries(investmentAnalyzer);

        // Simulate handling of stock tradings
        startStockTrading(stockTrader);
    }

    private static InvestmentQuery createQuery() {
        return InvestmentQuery.builder()
                .stockId("Stock" + random.nextInt(10000))
                .queryTime(LocalDateTime.now())
                .priority(random.nextInt(5))
                .investor(UUID.randomUUID().toString())
                .build();
    }

    private static void stockTradingWith200000Stocks(Trader stockTrader) {
        LogExecutionTime.start(() -> {
            System.out.print("Trading 200000 stocks...");
            for (int i = 0; i < 200000; i++) {
                stockTrader.enqueueStockForTrading(createQuery());
            }
            stockTrader.handleTradings();
        });
    }

    private static void initializeQueries(Analyzer investmentAnalyzer) {
        LogExecutionTime.start(() -> {
            System.out.print("Querying...");
            for (int i = 0; i < 100000; i++) {
                investmentAnalyzer.handleQuery(createQuery());
            }
        });
    }

    private static void handleQueries(Analyzer investmentAnalyzer) {
        LogExecutionTime.start(() -> {
            System.out.print("Analyzing queries...");
            investmentAnalyzer.analyzeQueries();
        });
    }

    private static void startStockTrading(Trader stockTrader) {
        LogExecutionTime.start(() -> {
            System.out.print("Handling tradings...");
            stockTrader.handleTradings();
        });
    }

}
