import java.util.ArrayList;

public class User {

    private String               name;
    private double               balance;      
    private Portfolio            portfolio;    
    private ArrayList<Transaction> transactions; 

    public User(String name, double initialBalance) {
        this.name         = name;
        this.balance      = initialBalance;
        this.portfolio    = new Portfolio();
        this.transactions = new ArrayList<Transaction>();
    }

    public String    getName()        { return name; }
    public double    getBalance()     { return balance; }
    public Portfolio getPortfolio()   { return portfolio; }
    public boolean buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;

        if (totalCost > balance) {
            System.out.println();
            System.out.printf("  [ERROR] Insufficient funds! Need $%.2f but you only have $%.2f%n",
                    totalCost, balance);
            return false;
        }
        balance -= totalCost;
        portfolio.addShares(stock.getSymbol(), quantity, stock.getPrice());
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
    public boolean sellStock(Stock stock, int quantity) {
        int owned = portfolio.getQuantity(stock.getSymbol());

        if (owned < quantity) {
            System.out.println();
            System.out.printf(
                    "  [ERROR] Not enough shares! You own %d share(s) of %s.%n",
                    owned, stock.getSymbol());
            return false;
        }
        portfolio.removeShares(stock.getSymbol(), quantity);
        double totalRevenue = stock.getPrice() * quantity;
        balance += totalRevenue;
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
    public void displayAccountSummary() {
        System.out.println();
        System.out.printf("  Trader   : %s%n", name);
        System.out.printf("  Cash     : $%,.2f%n", balance);
        System.out.printf("  Trades   : %d%n", transactions.size());
    }
}
