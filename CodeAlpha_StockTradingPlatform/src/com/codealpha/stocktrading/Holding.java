package com.codealpha.stocktrading;

/**
 * Represents a single stock holding in a user's portfolio.
 * Tracks how many shares are owned and what the average purchase price was,
 * enabling accurate profit/loss calculations even after multiple buys.
 */
public class Holding {
    private final Stock stock;
    private int quantity;
    private double averageBuyPrice; // Weighted average cost basis per share

    /**
     * Constructs a new Holding for the given stock.
     *
     * @param stock    The stock being held
     * @param quantity Number of shares initially purchased
     * @param buyPrice Price paid per share at time of purchase
     */
    public Holding(Stock stock, int quantity, double buyPrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.averageBuyPrice = buyPrice;
    }

    // ---- Getters ----

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    /**
     * Adds more shares to the holding and recalculates the weighted average buy price.
     *
     * @param qty      Number of additional shares being purchased
     * @param buyPrice Price paid per share for this purchase
     */
    public void buy(int qty, double buyPrice) {
        double totalCost = (this.quantity * this.averageBuyPrice) + (qty * buyPrice);
        this.quantity += qty;
        this.averageBuyPrice = totalCost / this.quantity;
    }

    /**
     * Reduces share count after a sale. Does not change the average buy price.
     *
     * @param qty Number of shares being sold
     * @throws IllegalArgumentException if selling more than owned
     */
    public void sell(int qty) {
        if (qty > this.quantity) {
            throw new IllegalArgumentException("Cannot sell more shares than owned.");
        }
        this.quantity -= qty;
    }

    /**
     * Returns the current total market value of this holding.
     * Formula: quantity * current stock price
     */
    public double getCurrentValue() {
        return quantity * stock.getCurrentPrice();
    }

    /**
     * Returns the total cost basis (amount originally invested) for current shares.
     * Formula: quantity * average buy price
     */
    public double getCostBasis() {
        return quantity * averageBuyPrice;
    }

    /**
     * Returns the unrealized profit or loss for this holding.
     * Formula: current value - cost basis
     */
    public double getProfitLoss() {
        return getCurrentValue() - getCostBasis();
    }

    @Override
    public String toString() {
        double currentPrice = stock.getCurrentPrice();
        double currentValue = getCurrentValue();
        double costBasis = getCostBasis();
        double profitLoss = getProfitLoss();
        double plPercent = costBasis > 0 ? (profitLoss / costBasis) * 100 : 0;

        return String.format("%-6s | %-22s | %6d | $%,10.2f | $%,10.2f | $%,12.2f | $%,12.2f (%+.2f%%)",
                stock.getSymbol(),
                stock.getName(),
                quantity,
                averageBuyPrice,
                currentPrice,
                costBasis,
                currentValue,
                plPercent);
    }
}
