// ============================================================
//  Holding.java
//  Represents a single stock position in the user's portfolio.
//  Tracks the symbol, quantity owned, and total cost paid.
// ============================================================

public class Holding {

    // --- Fields ---
    String stockSymbol;  // Which stock this holding is for
    int    quantity;     // Number of shares currently owned
    double totalCost;    // Sum of (price × qty) for every BUY

    // --- Constructor ---
    public Holding(String symbol, int qty, double cost) {
        this.stockSymbol = symbol;
        this.quantity    = qty;
        this.totalCost   = cost;
    }

    /** Returns the weighted average price paid per share. */
    public double getAvgBuyPrice() {
        return (quantity == 0) ? 0 : totalCost / quantity;
    }
}
