package com.codealpha.stocktrading;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Records the details of a single buy or sell trade.
 * Used to build a full audit trail / transaction history.
 */
public class Transaction {
    private final String type;        // "BUY" or "SELL"
    private final String stockSymbol;
    private final int quantity;
    private final double pricePerShare;
    private final LocalDateTime timestamp;

    /**
     * Constructs a new Transaction record.
     *
     * @param type         Trade type: "BUY" or "SELL"
     * @param stockSymbol  Ticker symbol of the stock traded
     * @param quantity     Number of shares traded
     * @param pricePerShare Price per share at time of execution
     */
    public Transaction(String type, String stockSymbol, int quantity, double pricePerShare) {
        this.type = type.toUpperCase();
        this.stockSymbol = stockSymbol.toUpperCase();
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.timestamp = LocalDateTime.now();
    }

    // ---- Getters ----

    public String getType() {
        return type;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /** Returns the total dollar value of this transaction. */
    public double getTotalValue() {
        return quantity * pricePerShare;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-19s | %-4s | %-6s | %6d | $%,10.2f | $%,12.2f",
                timestamp.format(fmt),
                type,
                stockSymbol,
                quantity,
                pricePerShare,
                getTotalValue());
    }
}
