// ============================================================
//  Stock.java
//  Represents a single tradeable stock in the market.
// ============================================================

public class Stock {

    // --- Fields ---
    private String symbol;      // Short ticker symbol, e.g. "AAPL"
    private String companyName; // Full company name
    private double price;       // Current market price per share

    // --- Constructor ---
    public Stock(String symbol, String companyName, double price) {
        this.symbol      = symbol;
        this.companyName = companyName;
        this.price       = price;
    }

    // --- Getters ---
    public String getSymbol()      { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice()       { return price; }

    // --- Setter (price can change) ---
    public void setPrice(double price) { this.price = price; }

    // --- Display a single stock row ---
    public void display() {
        System.out.printf("  %-6s  %-28s  $%,.2f%n",
                symbol, companyName, price);
    }
}
