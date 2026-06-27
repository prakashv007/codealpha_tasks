// ============================================================
//  StockMarket.java
//  Manages the list of available stocks.
//  Provides lookup, display, and a simple price-update helper.
// ============================================================

import java.util.ArrayList;

public class StockMarket {

    // --- The master list of stocks available to trade ---
    private ArrayList<Stock> stocks;

    // --- Constructor: pre-loads 10 well-known stocks ---
    public StockMarket() {
        stocks = new ArrayList<Stock>();

        // symbol, company, price (USD)
        stocks.add(new Stock("AAPL",  "Apple Inc.",                  189.30));
        stocks.add(new Stock("MSFT",  "Microsoft Corporation",       415.50));
        stocks.add(new Stock("GOOGL", "Alphabet Inc. (Google)",      175.80));
        stocks.add(new Stock("AMZN",  "Amazon.com Inc.",             182.60));
        stocks.add(new Stock("TSLA",  "Tesla Inc.",                  177.90));
        stocks.add(new Stock("META",  "Meta Platforms Inc.",         490.10));
        stocks.add(new Stock("NVDA",  "NVIDIA Corporation",         1075.00));
        stocks.add(new Stock("NFLX",  "Netflix Inc.",                625.40));
        stocks.add(new Stock("JPM",   "JPMorgan Chase & Co.",        197.70));
        stocks.add(new Stock("BRK.B", "Berkshire Hathaway Inc.",     384.20));
    }

    // -------------------------------------------------------
    //  getStocks – returns the full list (for Portfolio use)
    // -------------------------------------------------------
    public ArrayList<Stock> getStocks() { return stocks; }

    // -------------------------------------------------------
    //  findStock – returns a Stock by symbol, or null
    // -------------------------------------------------------
    public Stock findStock(String symbol) {
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                return stocks.get(i);
            }
        }
        return null;
    }

    // -------------------------------------------------------
    //  displayMarket – prints a numbered table of all stocks
    // -------------------------------------------------------
    public void displayMarket() {
        System.out.println();
        System.out.println("  ============================================================");
        System.out.println("                  LIVE STOCK MARKET PRICES");
        System.out.println("  ============================================================");
        System.out.printf("  %2s.  %-6s  %-28s  %s%n",
                "#", "SYMBOL", "COMPANY", "PRICE (USD)");
        System.out.println("  ------------------------------------------------------------");

        for (int i = 0; i < stocks.size(); i++) {
            System.out.printf("  %2d.  ", i + 1);
            stocks.get(i).display();
        }

        System.out.println("  ============================================================");
    }

    // -------------------------------------------------------
    //  simulatePriceChange
    //  Randomly nudges every stock price ±5 % to simulate
    //  live market movement.  Call from main menu if desired.
    // -------------------------------------------------------
    public void simulatePriceChange() {
        for (int i = 0; i < stocks.size(); i++) {
            Stock s        = stocks.get(i);
            // Random change between -5% and +5%
            double change  = (Math.random() * 10.0) - 5.0;   // -5 .. +5
            double newPrice = s.getPrice() * (1 + change / 100.0);
            // Round to 2 decimal places
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            s.setPrice(newPrice);
        }
        System.out.println();
        System.out.println("  [MARKET] Prices refreshed with latest changes.");
    }
}
