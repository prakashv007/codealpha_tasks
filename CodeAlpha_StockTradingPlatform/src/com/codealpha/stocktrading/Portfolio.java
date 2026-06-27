package com.codealpha.stocktrading;

import java.util.ArrayList;

/**
 * Manages a user's stock holdings and complete transaction history.
 * Uses ArrayLists to store both live holdings and the full trade log.
 */
public class Portfolio {
    // List of current stock holdings (one entry per unique stock owned)
    private final ArrayList<Holding> holdings;
    // Chronological list of all buy/sell transactions ever made
    private final ArrayList<Transaction> transactions;

    public Portfolio() {
        this.holdings = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    // ---- Accessors ----

    public ArrayList<Holding> getHoldings() {
        return holdings;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Finds a current holding by stock symbol (case-insensitive).
     *
     * @param symbol The stock ticker to look up
     * @return The Holding if found, or null if the stock is not currently owned
     */
    public Holding getHolding(String symbol) {
        for (Holding h : holdings) {
            if (h.getStock().getSymbol().equalsIgnoreCase(symbol)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Executes a stock purchase: adds/updates the holding and records the transaction.
     *
     * @param stock    The stock to purchase
     * @param quantity Number of shares to buy (must be > 0)
     */
    public void executeBuy(Stock stock, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Holding existing = getHolding(stock.getSymbol());
        if (existing != null) {
            // Already own this stock — update average buy price
            existing.buy(quantity, stock.getCurrentPrice());
        } else {
            // First purchase of this stock
            holdings.add(new Holding(stock, quantity, stock.getCurrentPrice()));
        }

        // Log the transaction
        transactions.add(new Transaction("BUY", stock.getSymbol(), quantity, stock.getCurrentPrice()));
    }

    /**
     * Executes a stock sale: reduces the holding (or removes it) and records the transaction.
     *
     * @param stock    The stock to sell
     * @param quantity Number of shares to sell (must be > 0 and <= owned)
     */
    public void executeSell(Stock stock, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Holding existing = getHolding(stock.getSymbol());
        if (existing == null) {
            throw new IllegalArgumentException("You do not own any shares of " + stock.getSymbol());
        }

        existing.sell(quantity);

        // Remove the holding entirely if all shares are sold
        if (existing.getQuantity() == 0) {
            holdings.remove(existing);
        }

        // Log the transaction
        transactions.add(new Transaction("SELL", stock.getSymbol(), quantity, stock.getCurrentPrice()));
    }

    /**
     * Calculates the total current market value of all holdings.
     */
    public double getTotalValue() {
        double total = 0;
        for (Holding h : holdings) {
            total += h.getCurrentValue();
        }
        return total;
    }

    /**
     * Calculates the total cost basis (original investment) of all current holdings.
     */
    public double getTotalCostBasis() {
        double total = 0;
        for (Holding h : holdings) {
            total += h.getCostBasis();
        }
        return total;
    }

    /**
     * Prints a formatted table of all current holdings with P&L details.
     */
    public void displayPortfolio() {
        if (holdings.isEmpty()) {
            System.out.println("\n  Your portfolio is currently empty. Buy some stocks to get started!");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════ PORTFOLIO HOLDINGS ══════════════════════════════════════════╗");
        System.out.printf("  %-6s | %-22s | %6s | %11s | %11s | %13s | %16s%n",
                "Symbol", "Company", "Shares", "Avg Buy Px", "Current Px", "Cost Basis", "Current Value (P&L)");
        System.out.println("  " + "─".repeat(99));

        for (Holding h : holdings) {
            System.out.println("  " + h);
        }

        System.out.println("  " + "─".repeat(99));

        double totalCost = getTotalCostBasis();
        double totalValue = getTotalValue();
        double totalPL = totalValue - totalCost;
        double plPercent = totalCost > 0 ? (totalPL / totalCost) * 100 : 0;

        System.out.printf("  Total Cost Basis    : $%,.2f%n", totalCost);
        System.out.printf("  Portfolio Value     : $%,.2f%n", totalValue);
        System.out.printf("  Total P&L           : $%,.2f (%+.2f%%)%n", totalPL, plPercent);
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Prints a formatted table of the complete transaction history.
     */
    public void displayTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("\n  No transactions recorded yet.");
            return;
        }

        System.out.println("\n╔══════════════════════ TRANSACTION HISTORY ══════════════════════╗");
        System.out.printf("  %-19s | %-4s | %-6s | %6s | %11s | %13s%n",
                "Timestamp", "Type", "Symbol", "Shares", "Price/Share", "Total Amount");
        System.out.println("  " + "─".repeat(68));

        for (Transaction t : transactions) {
            System.out.println("  " + t);
        }

        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
