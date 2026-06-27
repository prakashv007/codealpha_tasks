# Task 3 – Stock Trading Platform
**CodeAlpha Java Internship**

A console-based Stock Trading Platform built with pure Java, demonstrating core OOP principles.

---

## Features

| Feature | Details |
|---|---|
| Live Market View | Displays 10 stocks with current prices |
| Buy Stocks | Deducts cash and updates portfolio |
| Sell Stocks | Credits cash, validates sufficient shares |
| Portfolio Tracking | Shows quantity, avg cost, current value, and P&L |
| Transaction History | Full timestamped log of every trade |
| Price Simulation | Randomly nudges prices ±5% to mimic live markets |

---

## OOP Design

| Class | Responsibility |
|---|---|
| `Stock` | Holds symbol, company name, and current price |
| `StockMarket` | Manages the `ArrayList<Stock>` of tradeable stocks |
| `Transaction` | Records a single BUY/SELL event with timestamp |
| `Portfolio` | Tracks holdings (quantity + avg cost); computes P&L |
| `User` | Owns a balance, Portfolio, and `ArrayList<Transaction>` |
| `Main` | Entry point – interactive console menu loop |

---

## How to Run

```bash
# Step 1 – Compile all files
javac Stock.java StockMarket.java Transaction.java Portfolio.java User.java Main.java

# Step 2 – Run
java Main
```

Requires **Java 8** or later.

---

## Sample Session

```
  +============================================================+
  |          STOCK TRADING PLATFORM  v1.0                     |
  |          CodeAlpha Java Internship  –  Task 3              |
  +============================================================+
  |  Welcome, Trader!  Starting Balance: $10,000.00            |
  +============================================================+

  MAIN MENU
  1. View Market (All Stocks & Prices)
  2. Buy Stock
  3. Sell Stock
  4. View My Portfolio
  5. View Transaction History
  6. Refresh Market Prices (simulate live market)
  7. Exit
```

---

## Starting Balance
$10,000.00 – enough to experiment with multiple stocks.
