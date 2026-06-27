@echo off
echo ╔══════════════════════════════════════════════════════╗
echo ║     CODEALPHA STOCK TRADING PLATFORM  v1.0           ║
echo ╚══════════════════════════════════════════════════════╝
echo.
echo Compiling sources...
if not exist bin mkdir bin
javac -d bin src\com\codealpha\stocktrading\*.java
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Compilation failed. Please check the source files.
    pause
    exit /b %errorlevel%
)
echo [OK] Compilation successful.
echo.
echo Starting the platform...
echo.
java -cp bin com.codealpha.stocktrading.StockTradingPlatform
pause
