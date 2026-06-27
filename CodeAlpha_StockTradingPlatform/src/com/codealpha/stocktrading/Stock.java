package com.codealpha.stocktrading;

/**
 * Represents a Stock available in the stock market.
 * Stores the ticker symbol, company name, and current trading price.
 */
public class Stock {
    private final String symbol;
    private final String name;
    private double currentPrice;

    /**
     * Constructs a new Stock with the given symbol, name, and starting price.
     *
     * @param symbol       The ticker symbol (e.g., "AAPL")
     * @param name         The company name (e.g., "Apple Inc.")
     * @param currentPrice The initial market price per share
     */
    public Stock(String symbol, String name, double currentPrice) {
        this.symbol = symbol.toUpperCase();
        this.name = name;
        this.currentPrice = currentPrice;
    }

    // ---- Getters ----

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Updates the stock's current price.
     * Enforces a minimum price of $0.01 to prevent going negative or zero.
     *
     * @param currentPrice The new price per share
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = Math.max(0.01, currentPrice);
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-22s | $%,10.2f", symbol, name, currentPrice);
    }
}
