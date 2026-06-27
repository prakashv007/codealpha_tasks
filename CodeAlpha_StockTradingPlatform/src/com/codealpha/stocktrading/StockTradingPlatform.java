package com.codealpha.stocktrading;

import java.util.Scanner;

/**
 * Entry point for the CodeAlpha Stock Trading Platform.
 *
 * Provides a fully interactive console menu that lets users:
 *   1. View current market prices
 *   2. Buy stocks
 *   3. Sell stocks
 *   4. View their portfolio with live P&L
 *   5. View full transaction history
 *   6. Manually refresh/simulate market price fluctuations
 *   7. Exit
 *
 * Uses OOP principles throughout — each concern is handled by
 * a dedicated class (Stock, Holding, Transaction, Portfolio, User, StockMarket).
 */
public class StockTradingPlatform {

    /** Starting cash balance given to every new user. */
    private static final double INITIAL_BALANCE = 10_000.00;

    private final StockMarket market;
    private final User user;
    private final Scanner scanner;

    /**
     * Initializes the platform for the given username.
     *
     * @param username The trader's name entered at startup
     */
    public StockTradingPlatform(String username) {
        this.market = new StockMarket();
        this.user = new User(username, INITIAL_BALANCE);
        this.scanner = new Scanner(System.in);
    }

    // =========================================================
    //  Main Entry Point
    // =========================================================

    public static void main(String[] args) {
        Scanner startScanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     CODEALPHA STOCK TRADING PLATFORM  v1.0           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Enter your name to begin trading: ");
        String name = startScanner.nextLine().trim();
        if (name.isEmpty()) name = "Trader";

        StockTradingPlatform platform = new StockTradingPlatform(name);
        platform.run();
    }

    // =========================================================
    //  Main Application Loop
    // =========================================================

    /**
     * Starts the interactive menu loop.
     * Continues until the user selects "Exit".
     */
    public void run() {
        System.out.printf("%nWelcome, %s! You have been credited $%,.2f to start trading.%n",
                user.getUsername(), user.getBalance());

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("  → Choose an option (1-7): ");

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    handleBuy();
                    break;
                case 3:
                    handleSell();
                    break;
                case 4:
                    displayAccountStatus();
                    break;
                case 5:
                    user.getPortfolio().displayTransactionHistory();
                    break;
                case 6:
                    market.fluctuatePrices();
                    break;
                case 7:
                    System.out.println("\n  Thank you for trading with CodeAlpha. Goodbye!\n");
                    running = false;
                    break;
                default:
                    System.out.println("  [Error] Invalid option. Please enter a number from 1 to 7.");
            }
        }
        scanner.close();
    }

    // =========================================================
    //  Menu Display
    // =========================================================

    /** Prints the main action menu. */
    private void displayMenu() {
        System.out.println("\n┌─────────────────── MAIN MENU ───────────────────┐");
        System.out.println("│  1. View Available Stocks (Market Prices)        │");
        System.out.println("│  2. Buy Stock                                    │");
        System.out.println("│  3. Sell Stock                                   │");
        System.out.println("│  4. View Portfolio & Account Balance             │");
        System.out.println("│  5. View Transaction History                     │");
        System.out.println("│  6. Refresh Market Prices (Simulate Fluctuation) │");
        System.out.println("│  7. Exit                                         │");
        System.out.println("└──────────────────────────────────────────────────┘");
    }

    // =========================================================
    //  Buy Flow
    // =========================================================

    /**
     * Guides the user through purchasing shares of a stock.
     * Shows current price and available cash before confirming.
     */
    private void handleBuy() {
        System.out.println("\n── BUY STOCK ──────────────────────────────────────");
        System.out.print("  Enter stock symbol (e.g. AAPL): ");
        String symbol = scanner.nextLine().trim();

        Stock stock = market.findStock(symbol);
        if (stock == null) {
            System.out.printf("  [Error] '%s' was not found in the market.%n", symbol.toUpperCase());
            return;
        }

        System.out.printf("  %s  |  Current Price: $%,.2f%n", stock.getSymbol(), stock.getCurrentPrice());
        System.out.printf("  Available Cash : $%,.2f%n", user.getBalance());

        int qty = readInt("  Enter number of shares to buy: ");
        if (qty <= 0) {
            System.out.println("  [Error] Quantity must be a positive integer.");
            return;
        }

        boolean success = user.buyStock(stock, qty);
        if (success) {
            // Simulate market reaction after every trade
            market.fluctuatePrices();
        }
    }

    // =========================================================
    //  Sell Flow
    // =========================================================

    /**
     * Guides the user through selling shares of a stock they own.
     * Shows current holdings and price before confirming.
     */
    private void handleSell() {
        System.out.println("\n── SELL STOCK ─────────────────────────────────────");
        System.out.print("  Enter stock symbol to sell (e.g. TSLA): ");
        String symbol = scanner.nextLine().trim();

        Stock stock = market.findStock(symbol);
        if (stock == null) {
            System.out.printf("  [Error] '%s' was not found in the market.%n", symbol.toUpperCase());
            return;
        }

        Holding holding = user.getPortfolio().getHolding(stock.getSymbol());
        if (holding == null) {
            System.out.printf("  [Error] You do not own any shares of %s.%n", stock.getSymbol());
            return;
        }

        System.out.printf("  %s  |  Owned: %d shares  |  Current Price: $%,.2f%n",
                stock.getSymbol(), holding.getQuantity(), stock.getCurrentPrice());

        int qty = readInt("  Enter number of shares to sell: ");
        if (qty <= 0) {
            System.out.println("  [Error] Quantity must be a positive integer.");
            return;
        }

        boolean success = user.sellStock(stock, qty);
        if (success) {
            // Simulate market reaction after every trade
            market.fluctuatePrices();
        }
    }

    // =========================================================
    //  Account Status Display
    // =========================================================

    /** Displays the user's cash balance followed by their portfolio table. */
    private void displayAccountStatus() {
        System.out.println("\n╔═══════════════ ACCOUNT STATUS ════════════════╗");
        System.out.printf("  Trader     : %s%n", user.getUsername());
        System.out.printf("  Cash Balance: $%,.2f%n", user.getBalance());
        System.out.println("╚═════════════════════════════════════════════════╝");
        user.getPortfolio().displayPortfolio();
    }

    // =========================================================
    //  Input Utility
    // =========================================================

    /**
     * Safely reads an integer from the console, looping until valid input is given.
     *
     * @param prompt The message to show the user before reading input
     * @return A valid integer entered by the user
     */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Please enter a valid whole number.");
            }
        }
    }
}
