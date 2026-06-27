# CodeAlpha Stock Trading Platform

A Java console-based Stock Trading Platform built as part of the **CodeAlpha Java Programming Internship**.

---

## Features

| Feature | Details |
|---|---|
| **Live Market Prices** | View 8 stocks (AAPL, GOOGL, MSFT, AMZN, TSLA, NVDA, META, NFLX) with current prices |
| **Buy & Sell Stocks** | Purchase or liquidate shares interactively via the console menu |
| **Portfolio Tracking** | See owned shares, average buy price, current value, and per-stock P&L |
| **Profit & Loss** | Real-time P&L in both absolute dollars and percentage, per holding and overall |
| **Transaction History** | Full audit log of every buy and sell with timestamps and total amounts |
| **Market Simulation** | Prices fluctuate randomly ±3.5% after each trade or on manual refresh |

---

## Project Structure

```
CodeAlpha_StockTradingPlatform/
│
├── src/
│   └── com/
│       └── codealpha/
│           └── stocktrading/
│               ├── Stock.java                  # Market stock (symbol, name, price)
│               ├── Holding.java                # Owned position (qty, cost basis, P&L)
│               ├── Transaction.java            # Immutable trade log entry
│               ├── Portfolio.java              # Holdings + transactions (uses ArrayList)
│               ├── User.java                   # Trader account (balance + buy/sell)
│               ├── StockMarket.java            # Market data + price fluctuation
│               └── StockTradingPlatform.java   # Console UI and main entry point
│
├── bin/                    (auto-generated during compilation)
├── run.bat                 # Windows build-and-run script
└── README.md
```

---

## OOP Design

| Class | Responsibility |
|---|---|
| `Stock` | Data model for a single market stock |
| `Holding` | Tracks shares owned + weighted average cost basis |
| `Transaction` | Immutable record of a completed trade |
| `Portfolio` | Manages `ArrayList<Holding>` and `ArrayList<Transaction>` |
| `User` | Owns a `Portfolio`; enforces balance and share checks before trading |
| `StockMarket` | Owns the `ArrayList<Stock>`; handles display and price simulation |
| `StockTradingPlatform` | `main()` entry point; drives the interactive menu loop |

---

## How to Run

### Option 1 — Windows Batch File (easiest)
Double-click `run.bat`, or from a command prompt:
```cmd
run.bat
```

### Option 2 — Manual

**Step 1: Compile**
```cmd
mkdir bin
javac -d bin src\com\codealpha\stocktrading\*.java
```

**Step 2: Run**
```cmd
java -cp bin com.codealpha.stocktrading.StockTradingPlatform
```

> **Requirements:** Java 8 or higher.
