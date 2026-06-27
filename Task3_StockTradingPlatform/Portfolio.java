// ============================================================
//  Portfolio.java
//  Tracks all holdings: which stocks the user owns and at what
//  average cost.  Also computes current value and P&L.
//
//  Uses the standalone Holding class (Holding.java) instead of
//  an inner class, so no Portfolio$Holding.class is produced.
// ============================================================

import java.util.ArrayList;

public class Portfolio {

    // --- List of current stock positions ---
    private ArrayList<Holding> holdings;

    // --- Constructor ---
    public Portfolio() {
        holdings = new ArrayList<Holding>();
    }

    // -------------------------------------------------------
    //  findHolding – returns the Holding for a symbol, or null
    // -------------------------------------------------------
    private Holding findHolding(String symbol) {
        for (int i = 0; i < holdings.size(); i++) {
            if (holdings.get(i).stockSymbol.equals(symbol)) {
                return holdings.get(i);
            }
        }
        return null;
    }

    // -------------------------------------------------------
    //  addShares – called when user buys stock
    // -------------------------------------------------------
    public void addShares(String symbol, int qty, double pricePerShare) {
        Holding h = findHolding(symbol);
        if (h == null) {
            // First time buying this stock – create a new Holding
            holdings.add(new Holding(symbol, qty, qty * pricePerShare));
        } else {
            // Already own some – update quantity and total cost
            h.quantity  += qty;
            h.totalCost += qty * pricePerShare;
        }
    }

    // -------------------------------------------------------
    //  removeShares – called when user sells stock.
    //  Returns false if not enough shares owned.
    // -------------------------------------------------------
    public boolean removeShares(String symbol, int qty) {
        Holding h = findHolding(symbol);
        if (h == null || h.quantity < qty) {
            return false;   // Cannot sell – insufficient shares
        }

        // Reduce cost proportionally using the average buy price
        double costPerShare = h.getAvgBuyPrice();
        h.quantity  -= qty;
        h.totalCost -= qty * costPerShare;

        // Remove the Holding entry entirely if fully sold out
        if (h.quantity == 0) {
            holdings.remove(h);
        }
        return true;
    }

    // -------------------------------------------------------
    //  getQuantity – how many shares of a stock does user own?
    // -------------------------------------------------------
    public int getQuantity(String symbol) {
        Holding h = findHolding(symbol);
        return (h == null) ? 0 : h.quantity;
    }

    // -------------------------------------------------------
    //  isEmpty – used in Main to gate the sell menu option
    // -------------------------------------------------------
    public boolean isEmpty() {
        return holdings.isEmpty();
    }

    // -------------------------------------------------------
    //  displayPortfolio – shows all current holdings with P&L.
    //  Requires the market's stock list to look up current price.
    // -------------------------------------------------------
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

            // Look up the current market price for this stock
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

            // Format P&L with explicit +/- sign
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
