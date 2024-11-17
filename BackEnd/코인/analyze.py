import pyupbit
import pandas as pd
import numpy as np

# 업비트 거래 수수료
fee = 0.0005  # 0.05% 수수료

# MACD 계산 함수
def calculate_macd(df, fast_period=12, slow_period=26, signal_period=9):
    exp1 = df['close'].ewm(span=fast_period, adjust=False).mean()
    exp2 = df['close'].ewm(span=slow_period, adjust=False).mean()
    macd = exp1 - exp2
    signal = macd.ewm(span=signal_period, adjust=False).mean()
    return macd, signal

# 백테스팅 함수
def backtest(ticker, df):
    df['macd'], df['signal'] = calculate_macd(df)

    initial_balance = 1000000  # 초기 자본 (백만원)
    balance = initial_balance
    position = 0  # 보유 코인 수량

    trades = []  # 거래 기록 저장

    for i in range(1, len(df)):
        prev_macd, prev_signal = df['macd'].iloc[i-1], df['signal'].iloc[i-1]
        curr_macd, curr_signal = df['macd'].iloc[i], df['signal'].iloc[i]
        
        # 매수 조건: MACD가 시그널 상향 돌파
        if prev_macd <= prev_signal and curr_macd > curr_signal and position == 0:
            position = (balance * (1 - fee)) / df['close'].iloc[i]  # 수수료 반영하여 매수
            balance = 0
            trades.append({'date': df.index[i], 'type': 'buy', 'price': df['close'].iloc[i]})
            print(f"[{df.index[i]}] 매수: {ticker} @ {df['close'].iloc[i]}")

        # 매도 조건: MACD가 시그널 하향 돌파
        elif prev_macd >= prev_signal and curr_macd < curr_signal and position > 0:
            balance = position * df['close'].iloc[i] * (1 - fee)  # 수수료 반영하여 매도
            profit = (balance - initial_balance) / initial_balance * 100  # 수익률 계산
            position = 0
            trades.append({'date': df.index[i], 'type': 'sell', 'price': df['close'].iloc[i], 'profit': profit})
            print(f"[{df.index[i]}] 매도: {ticker} @ {df['close'].iloc[i]}, 수익률: {profit:.2f}%")
    
    # 최종 잔액 및 수익률 계산
    final_balance = balance if balance > 0 else position * df['close'].iloc[-1] * (1 - fee)
    final_profit_percent = (final_balance - initial_balance) / initial_balance * 100
    
    return pd.DataFrame(trades), final_profit_percent

def run_backtests(tickers):
    results = {}
    for ticker in tickers:
        try:
            df = pyupbit.get_ohlcv(ticker, interval="minute15", count=2880)
            if df is None or df.empty:
                print(f"{ticker}: 데이터를 불러오는데 실패했습니다.")
                continue
            
            trades_df, final_profit = backtest(ticker, df)
            results[ticker] = final_profit
            print(f"{ticker} 백테스팅 완료: 최종 수익률 {final_profit:.2f}%")
        except Exception as e:
            print(f"{ticker} 오류 발생: {e}")
    
    return results

try:
    tickers = ["KRW-BTC", "KRW-ETH", "KRW-XRP", "KRW-ADA", "KRW-DOGE"]
    results = run_backtests(tickers)
    
    print("\n각 코인별 최종 수익률:")
    for ticker, profit in results.items():
        print(f"{ticker}: {profit:.2f}%")
    
    best_coin = max(results, key=results.get)
    worst_coin = min(results, key=results.get)
    print(f"\n최고 수익률 코인: {best_coin} ({results[best_coin]:.2f}%)")
    print(f"최저 수익률 코인: {worst_coin} ({results[worst_coin]:.2f}%)")
    
    average_profit = sum(results.values()) / len(results)
    print(f"\n평균 수익률: {average_profit:.2f}%")
    
except Exception as e:
    print(f"전체 프로세스 오류 발생: {e}")
