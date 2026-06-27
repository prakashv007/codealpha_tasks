import java.util.ArrayList;

public class StockMarket {
    private ArrayList<Stock> stocks;
    public StockMarket() {
        stocks = new ArrayList<Stock>();
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
    public ArrayList<Stock> getStocks() { return stocks; }

    public Stock findStock(String symbol) {
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                return stocks.get(i);
            }
        }
        return null;
    }
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
    public void simulatePriceChange() {
        for (int i = 0; i < stocks.size(); i++) {
            Stock s        = stocks.get(i);
            double change  = (Math.random() * 10.0) - 5.0;  
            double newPrice = s.getPrice() * (1 + change / 100.0);
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            s.setPrice(newPrice);
        }
        System.out.println();
        System.out.println("  [MARKET] Prices refreshed with latest changes.");
    }
}
