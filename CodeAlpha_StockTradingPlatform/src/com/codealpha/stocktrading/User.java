package com.codealpha.stocktrading;

/**
 * Represents a registered user (trader) on the platform.
 * Manages their cash balance and delegates all portfolio operations
 * to the embedded Portfolio object.
 */
public class User {
    private final String username;
    private double balance;           // Available cash for trading
    private final Portfolio portfolio;

    /**
     * Constructs a new User with a starting cash balance.
     *
     * @param username       The trader's display name
     * @param initialBalance Starting cash amount (e.g., $10,000)
     */
    public User(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;
        this.portfolio = new Portfolio();
    }

    // ---- Getters ----

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Attempts to buy shares of a given stock.
     * Validates that the user has sufficient funds before executing.
     *
     * @param stock    The stock to buy
     * @param quantity Number of shares to purchase
     * @return true if the purchase succeeded, false otherwise
     */
    public boolean buyStock(Stock stock, int quantity) {
        if (stock == null || quantity <= 0) {
            System.out.println("  [Error] Invalid stock or quantity.");
            return false;
        }

        double totalCost = stock.getCurrentPrice() * quantity;
        if (balance < totalCost) {
            System.out.printf("  [Error] Insufficient funds.  Required: $%,.2f  |  Available: $%,.2f%n",
                    totalCost, balance);
            return false;
        }

        // Deduct cost and add to portfolio
        balance -= totalCost;
        portfolio.executeBuy(stock, quantity);
        System.out.printf("  [Success] Bought %d share(s) of %s at $%,.2f/share.  Total: $%,.2f%n",
                quantity, stock.getSymbol(), stock.getCurrentPrice(), totalCost);
        return true;
    }

    /**
     * Attempts to sell shares of a given stock.
     * Validates that the user owns enough shares before executing.
     *
     * @param stock    The stock to sell
     * @param quantity Number of shares to sell
     * @return true if the sale succeeded, false otherwise
     */
    public boolean sellStock(Stock stock, int quantity) {
        if (stock == null || quantity <= 0) {
            System.out.println("  [Error] Invalid stock or quantity.");
            return false;
        }

        Holding holding = portfolio.getHolding(stock.getSymbol());
        if (holding == null) {
            System.out.printf("  [Error] You do not own any shares of %s.%n", stock.getSymbol());
            return false;
        }
        if (holding.getQuantity() < quantity) {
            System.out.printf("  [Error] You only own %d share(s) of %s, but tried to sell %d.%n",
                    holding.getQuantity(), stock.getSymbol(), quantity);
            return false;
        }

        // Credit the proceeds and remove from portfolio
        double proceeds = stock.getCurrentPrice() * quantity;
        balance += proceeds;
        portfolio.executeSell(stock, quantity);
        System.out.printf("  [Success] Sold %d share(s) of %s at $%,.2f/share.  Proceeds: $%,.2f%n",
                quantity, stock.getSymbol(), stock.getCurrentPrice(), proceeds);
        return true;
    }
}
