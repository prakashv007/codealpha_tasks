import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Holding> holdings;
    public Portfolio() {
        holdings = new ArrayList<Holding>();
    }
    private Holding findHolding(String symbol) {
        for (int i = 0; i < holdings.size(); i++) {
            if (holdings.get(i).stockSymbol.equals(symbol)) {
                return holdings.get(i);
            }
        }
        return null;
    }
    public void addShares(String symbol, int qty, double pricePerShare) {
        Holding h = findHolding(symbol);
        if (h == null) {
            holdings.add(new Holding(symbol, qty, qty * pricePerShare));
        } else {
            h.quantity  += qty;
            h.totalCost += qty * pricePerShare;
        }
    }
    public boolean removeShares(String symbol, int qty) {
        Holding h = findHolding(symbol);
        if (h == null || h.quantity < qty) {
            return false;  
        }
        double costPerShare = h.getAvgBuyPrice();
        h.quantity  -= qty;
        h.totalCost -= qty * costPerShare;

        if (h.quantity == 0) {
            holdings.remove(h);
        }
        return true;
    }
    public int getQuantity(String symbol) {
        Holding h = findHolding(symbol);
        return (h == null) ? 0 : h.quantity;
    }
    public boolean isEmpty() {
        return holdings.isEmpty();
    }
    public void displayPortfolio(ArrayList<Stock> marketStocks) {
        System.out.println();
        System.out.println("  ============================================================");
        System.out.println("                     YOUR PORTFOLIO");
        System.out.println("  ============================================================");

        if (holdings.isEmpty()) {
            System.out.println("  (No stocks held currently.)");
            System.out.println("  ============================================================");
            return;
        }

        System.out.printf("  %-6s  %5s  %10s  %10s  %10s  %7s%n",
                "SYMBOL", "QTY", "AVG COST", "CUR PRICE", "CUR VALUE", "P&L");
        System.out.println("  ------------------------------------------------------------");

        double totalValue = 0;
        double totalPnL   = 0;

        for (int i = 0; i < holdings.size(); i++) {
            Holding h = holdings.get(i);
            double currentPrice = 0;
            for (int j = 0; j < marketStocks.size(); j++) {
                if (marketStocks.get(j).getSymbol().equals(h.stockSymbol)) {
                    currentPrice = marketStocks.get(j).getPrice();
                    break;
                }
            }

            double currentValue = h.quantity * currentPrice;
            double pnl          = currentValue - h.totalCost;

            totalValue += currentValue;
            totalPnL   += pnl;
            String pnlStr = String.format("%s$%.2f",
                    (pnl >= 0 ? "+" : "-"), Math.abs(pnl));

            System.out.printf("  %-6s  %5d  %10.2f  %10.2f  %10.2f  %7s%n",
                    h.stockSymbol, h.quantity, h.getAvgBuyPrice(),
                    currentPrice, currentValue, pnlStr);
        }

        System.out.println("  ------------------------------------------------------------");
        System.out.printf("  %-6s  %5s  %10s  %10s  %10.2f  %s$%.2f%n",
                "TOTAL", "", "", "",
                totalValue,
                (totalPnL >= 0 ? "+" : "-"), Math.abs(totalPnL));
        System.out.println("  ============================================================");
    }
}
