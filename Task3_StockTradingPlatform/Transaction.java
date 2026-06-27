// ============================================================
//  Transaction.java
//  Records a single buy or sell event with all relevant data.
// ============================================================

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    // --- Transaction types ---
    public static final String BUY  = "BUY";
    public static final String SELL = "SELL";

    // --- Fields ---
    private String type;           // "BUY" or "SELL"
    private String stockSymbol;    // Which stock was traded
    private int    quantity;       // Number of shares
    private double pricePerShare;  // Price at time of trade
    private String timestamp;      // When the trade happened

    // --- Constructor ---
    public Transaction(String type, String stockSymbol,
                       int quantity, double pricePerShare) {
        this.type          = type;
        this.stockSymbol   = stockSymbol;
        this.quantity      = quantity;
        this.pricePerShare = pricePerShare;

        // Capture current date-time as a readable string
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(fmt);
    }

    // --- Getters ---
    public String getType()           { return type; }
    public String getStockSymbol()    { return stockSymbol; }
    public int    getQuantity()       { return quantity; }
    public double getPricePerShare()  { return pricePerShare; }
    public String getTimestamp()      { return timestamp; }

    /** Total value of this transaction (quantity × price). */
    public double getTotalValue() {
        return quantity * pricePerShare;
    }

    // --- Display a transaction row ---
    public void display() {
        System.out.printf("  %-4s  %-6s  %5d shares  @ $%8.2f  =  $%10.2f  [%s]%n",
                type, stockSymbol, quantity, pricePerShare,
                getTotalValue(), timestamp);
    }
}
