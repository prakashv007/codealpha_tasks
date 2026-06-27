package com.codealpha.stocktrading;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simulates the stock market.
 * Holds the list of all tradable stocks, displays market prices,
 * and applies random price fluctuations after each trade to mimic
 * real-world market dynamics.
 */
public class StockMarket {
    private final ArrayList<Stock> stocks; // All available stocks in the market
    private final Random random;

    public StockMarket() {
        this.stocks = new ArrayList<>();
        this.random = new Random();
        initializeStocks();
    }

    /**
     * Seeds the market with a default set of well-known stocks.
     * Prices are representative but simulated starting values.
     */
    private void initializeStocks() {
        stocks.add(new Stock("AAPL",  "Apple Inc.",           175.50));
        stocks.add(new Stock("GOOGL", "Alphabet Inc.",        150.25));
        stocks.add(new Stock("MSFT",  "Microsoft Corp.",      415.00));
        stocks.add(new Stock("AMZN",  "Amazon.com, Inc.",     180.10));
        stocks.add(new Stock("TSLA",  "Tesla Inc.",           170.80));
        stocks.add(new Stock("NVDA",  "NVIDIA Corp.",         875.00));
        stocks.add(new Stock("META",  "Meta Platforms, Inc.", 485.30));
        stocks.add(new Stock("NFLX",  "Netflix Inc.",         620.75));
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    /**
     * Searches for a stock by symbol (case-insensitive).
     *
     * @param symbol The ticker to search for
     * @return The matching Stock, or null if not found
     */
    public Stock findStock(String symbol) {
        if (symbol == null) return null;
        for (Stock s : stocks) {
            if (s.getSymbol().equalsIgnoreCase(symbol.trim())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Prints a formatted table of all listed stocks and their current prices.
     */
    public void displayMarket() {
        System.out.println("\n╔══════════════ LIVE MARKET PRICES ══════════════╗");
        System.out.printf("  %-6s | %-22s | %11s%n", "Symbol", "Company", "Price");
        System.out.println("  " + "─".repeat(46));
        for (Stock s : stocks) {
            System.out.println("  " + s);
        }
        System.out.println("╚═════════════════════════════════════════════════╝");
    }

    /**
     * Randomly fluctuates all stock prices between -3.5% and +3.5%
     * to simulate live market activity after each transaction or on demand.
     */
    public void fluctuatePrices() {
        for (Stock s : stocks) {
            // Generate a change factor between -0.035 and +0.035
            double changeFactor = (random.nextDouble() * 7.0 - 3.5) / 100.0;
            s.setCurrentPrice(s.getCurrentPrice() * (1.0 + changeFactor));
        }
        System.out.println("\n  [Market Update] Prices have shifted — check the market for latest values.");
    }
}
