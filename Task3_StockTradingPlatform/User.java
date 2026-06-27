// ============================================================
//  User.java
//  Represents the trader: holds account balance, a Portfolio,
//  and a full transaction history.
// ============================================================

import java.util.ArrayList;

public class User {

    // --- Fields ---
    private String               name;
    private double               balance;       // Available cash (USD)
    private Portfolio            portfolio;     // Current stock holdings
    private ArrayList<Transaction> transactions; // Full trade history

    // --- Constructor ---
    public User(String name, double initialBalance) {
        this.name         = name;
        this.balance      = initialBalance;
        this.portfolio    = new Portfolio();
        this.transactions = new ArrayList<Transaction>();
    }

    // --- Getters ---
    public String    getName()        { return name; }
    public double    getBalance()     { return balance; }
    public Portfolio getPortfolio()   { return portfolio; }

    // -------------------------------------------------------
    //  buyStock
    //  Deducts cash, updates portfolio, logs transaction.
    //  Returns false if the user cannot afford the purchase.
    // -------------------------------------------------------
    public boolean buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;

        if (totalCost > balance) {
            System.out.println();
            System.out.printf("  [ERROR] Insufficient funds! Need $%.2f but you only have $%.2f%n",
                    totalCost, balance);
            return false;
        }

        // Deduct balance
        balance -= totalCost;

        // Update portfolio
        portfolio.addShares(stock.getSymbol(), quantity, stock.getPrice());

        // Record the transaction
        Transaction t = new Transaction(Transaction.BUY,
                stock.getSymbol(), quantity, stock.getPrice());
        transactions.add(t);

        System.out.println();
        System.out.printf("  [SUCCESS] Bought %d share(s) of %s at $%.2f each.%n",
                quantity, stock.getSymbol(), stock.getPrice());
        System.out.printf("  Total spent: $%.2f  |  Remaining balance: $%.2f%n",
                totalCost, balance);
        return true;
    }

    // -------------------------------------------------------
    //  sellStock
    //  Adds cash, updates portfolio, logs transaction.
    //  Returns false if the user does not own enough shares.
    // -------------------------------------------------------
    public boolean sellStock(Stock stock, int quantity) {
        int owned = portfolio.getQuantity(stock.getSymbol());

        if (owned < quantity) {
            System.out.println();
            System.out.printf(
                    "  [ERROR] Not enough shares! You own %d share(s) of %s.%n",
                    owned, stock.getSymbol());
            return false;
        }

        // Remove from portfolio (uses avg cost reduction internally)
        portfolio.removeShares(stock.getSymbol(), quantity);

        // Credit balance
        double totalRevenue = stock.getPrice() * quantity;
        balance += totalRevenue;

        // Record the transaction
        Transaction t = new Transaction(Transaction.SELL,
                stock.getSymbol(), quantity, stock.getPrice());
        transactions.add(t);

        System.out.println();
        System.out.printf("  [SUCCESS] Sold %d share(s) of %s at $%.2f each.%n",
                quantity, stock.getSymbol(), stock.getPrice());
        System.out.printf("  Total received: $%.2f  |  New balance: $%.2f%n",
                totalRevenue, balance);
        return true;
    }

    // -------------------------------------------------------
    //  displayTransactionHistory – prints the full trade log
    // -------------------------------------------------------
    public void displayTransactionHistory() {
        System.out.println();
        System.out.println("  ============================================================");
        System.out.println("                  TRANSACTION HISTORY");
        System.out.println("  ============================================================");

        if (transactions.isEmpty()) {
            System.out.println("  (No transactions yet.)");
            System.out.println("  ============================================================");
            return;
        }

        System.out.printf("  %-4s  %-6s  %13s  %12s  %12s  %s%n",
                "TYPE", "SYMBOL", "QUANTITY", "PRICE/SHARE", "TOTAL", "TIMESTAMP");
        System.out.println("  ------------------------------------------------------------");

        for (int i = 0; i < transactions.size(); i++) {
            transactions.get(i).display();
        }

        System.out.println("  ============================================================");
    }

    // -------------------------------------------------------
    //  displayAccountSummary – one-liner cash + portfolio count
    // -------------------------------------------------------
    public void displayAccountSummary() {
        System.out.println();
        System.out.printf("  Trader   : %s%n", name);
        System.out.printf("  Cash     : $%,.2f%n", balance);
        System.out.printf("  Trades   : %d%n", transactions.size());
    }
}
