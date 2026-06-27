import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StockMarket market = new StockMarket();
        User        user   = new User("Trader", 10_000.00); 

        System.out.println();
        System.out.println("  +============================================================+");
        System.out.println("  |          STOCK TRADING PLATFORM  v1.0                     |");
        System.out.println("  |          CodeAlpha Java Internship  –  Task 3              |");
        System.out.println("  +============================================================+");
        System.out.printf( "  |  Welcome, %s!  Starting Balance: $%,.2f        |%n",
                user.getName(), user.getBalance());
        System.out.println("  +============================================================+");

        int choice = 0;

        while (choice != 7) {
            System.out.println();
            System.out.println("  ============================================================");
            System.out.println("                         MAIN MENU");
            System.out.println("  ============================================================");
            System.out.println("  1. View Market (All Stocks & Prices)");
            System.out.println("  2. Buy Stock");
            System.out.println("  3. Sell Stock");
            System.out.println("  4. View My Portfolio");
            System.out.println("  5. View Transaction History");
            System.out.println("  6. Refresh Market Prices (simulate live market)");
            System.out.println("  7. Exit");
            System.out.println("  ============================================================");
            user.displayAccountSummary();

            System.out.println();
            System.out.print("  Enter your choice (1-7): ");

            if (!scanner.hasNextInt()) {
                System.out.println("  [ERROR] Please enter a number between 1 and 7.");
                scanner.nextLine();  
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                market.displayMarket();

            } else if (choice == 2) {
                market.displayMarket();

                System.out.print("  Enter stock symbol to BUY (e.g. AAPL): ");
                String symbol = scanner.nextLine().trim().toUpperCase();

                Stock selected = market.findStock(symbol);
                if (selected == null) {
                    System.out.println("  [ERROR] Stock symbol \"" + symbol + "\" not found in the market.");
                } else {
                    System.out.printf("  %s – %s  |  Current Price: $%.2f%n",
                            selected.getSymbol(), selected.getCompanyName(), selected.getPrice());
                    System.out.printf("  Your Balance: $%.2f%n", user.getBalance());
                    System.out.print("  Enter quantity to buy: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("  [ERROR] Invalid quantity.");
                        scanner.nextLine();
                    } else {
                        int qty = scanner.nextInt();
                        scanner.nextLine();
                        if (qty <= 0) {
                            System.out.println("  [ERROR] Quantity must be greater than zero.");
                        } else {
                            user.buyStock(selected, qty);
                        }
                    }
                }

            } else if (choice == 3) {
                if (user.getPortfolio().isEmpty()) {
                    System.out.println();
                    System.out.println("  [INFO] You have no stocks to sell. Buy some first!");
                } else {
                    user.getPortfolio().displayPortfolio(market.getStocks());

                    System.out.print("  Enter stock symbol to SELL (e.g. TSLA): ");
                    String symbol = scanner.nextLine().trim().toUpperCase();

                    Stock selected = market.findStock(symbol);
                    if (selected == null) {
                        System.out.println("  [ERROR] Stock symbol \"" + symbol + "\" not found.");
                    } else {
                        int owned = user.getPortfolio().getQuantity(symbol);
                        System.out.printf("  %s – Current Price: $%.2f  |  You own: %d share(s)%n",
                                symbol, selected.getPrice(), owned);
                        System.out.print("  Enter quantity to sell: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("  [ERROR] Invalid quantity.");
                            scanner.nextLine();
                        } else {
                            int qty = scanner.nextInt();
                            scanner.nextLine();
                            if (qty <= 0) {
                                System.out.println("  [ERROR] Quantity must be greater than zero.");
                            } else {
                                user.sellStock(selected, qty);
                            }
                        }
                    }
                }

            } else if (choice == 4) {
                user.getPortfolio().displayPortfolio(market.getStocks());

            } else if (choice == 5) {
                user.displayTransactionHistory();

            } else if (choice == 6) {
                market.simulatePriceChange();

            } else if (choice == 7) {
                System.out.println();
                System.out.println("  ============================================================");
                System.out.println("   Thank you for using the Stock Trading Platform!");
                System.out.printf( "   Final Balance: $%,.2f%n", user.getBalance());
                System.out.println("   Goodbye!");
                System.out.println("  ============================================================");
                System.out.println();

            } else {
                System.out.println("  [ERROR] Invalid choice. Enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }
}
