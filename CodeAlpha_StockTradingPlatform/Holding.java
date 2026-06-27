public class Holding {

   
    String stockSymbol;  
    int    quantity;    
    double totalCost;   

    public Holding(String symbol, int qty, double cost) {
        this.stockSymbol = symbol;
        this.quantity    = qty;
        this.totalCost   = cost;
    }

    public double getAvgBuyPrice() {
        return (quantity == 0) ? 0 : totalCost / quantity;
    }
}
