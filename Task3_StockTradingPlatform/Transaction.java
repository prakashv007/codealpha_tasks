import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    public static final String BUY  = "BUY";
    public static final String SELL = "SELL";

    private String type;           
    private String stockSymbol;   
    private int    quantity;      
    private double pricePerShare;  
    private String timestamp;      

    public Transaction(String type, String stockSymbol,
                       int quantity, double pricePerShare) {
        this.type          = type;
        this.stockSymbol   = stockSymbol;
        this.quantity      = quantity;
        this.pricePerShare = pricePerShare;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(fmt);
    }

    public String getType()           { return type; }
    public String getStockSymbol()    { return stockSymbol; }
    public int    getQuantity()       { return quantity; }
    public double getPricePerShare()  { return pricePerShare; }
    public String getTimestamp()      { return timestamp; }

    public double getTotalValue() {
        return quantity * pricePerShare;
    }

    public void display() {
        System.out.printf("  %-4s  %-6s  %5d shares  @ $%8.2f  =  $%10.2f  [%s]%n",
                type, stockSymbol, quantity, pricePerShare,
                getTotalValue(), timestamp);
    }
}
