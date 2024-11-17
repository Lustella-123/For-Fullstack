import pyupbit
import pandas as pd
import numpy as np
from itertools import product

fee = 0.0005

def calculate_psychological_index(df, period):
    df['up_days'] = np.where(df['close'] > df['close'].shift(1), 1, 0)
    df['psychological'] = df['up_days'].rolling(window=period).sum() / period * 100
    return df['psychological']

def calculate_macd(df, fast_period, slow_period, signal_period):
    exp1 = df['close'].ewm(span=fast_period, adjust=False).mean()
    exp2 = df['close'].ewm(span=slow_period, adjust=False).mean()
    macd = exp1 - exp2
    signal = macd.ewm(span=signal_period, adjust=False).mean()
    return macd, signal

def backtest(df, psych_period, psych_threshold, macd_fast, macd_slow, macd_signal):
    df['psychological'] = calculate_psychological_index(df, psych_period)
    df['macd'], df['signal'] = calculate_macd(df, macd_fast, macd_slow, macd_signal)

    initial_balance = 1000000
    balance = initial_balance
    position = 0

    for i in range(1, len(df)):
        prev_macd, prev_signal = df['macd'].iloc[i-1], df['signal'].iloc[i-1]
        curr_macd, curr_signal = df['macd'].iloc[i], df['signal'].iloc[i]
        
        # 매수 조건
        if df['psychological'].iloc[i] >= psych_threshold and prev_macd <= prev_signal and curr_macd > curr_signal and position == 0:
            position = (balance * (1 - fee)) / df['close'].iloc[i]
            balance = 0

        # 매도 조건
        elif df['psychological'].iloc[i] <= psych_threshold and prev_macd >= prev_signal and curr_macd < curr_signal and position > 0:
            balance = position * df['close'].iloc[i] * (1 - fee)
            position = 0
    
    # 최종 잔액 및 수익률 계산
    final_balance = balance if balance > 0 else position * df['close'].iloc[-1] * (1 - fee)
    final_profit_percent = (final_balance - initial_balance) / initial_balance * 100
    
    return final_profit_percent

def optimize_parameters(ticker):
    df = pyupbit.get_ohlcv(ticker, interval="minute15", count=2880)
    if df is None or df.empty:
        raise ValueError(f"{ticker}: 데이터를 불러오는데 실패했습니다.")

    psych_periods = range(5, 21, 5)
    psych_thresholds = range(40, 61, 5)
    macd_fast_periods = range(8, 17, 2)
    macd_slow_periods = range(20, 31, 2)
    macd_signal_periods = range(7, 12)

    best_profit = -float('inf')
    best_params = None

    for params in product(psych_periods, psych_thresholds, macd_fast_periods, macd_slow_periods, macd_signal_periods):
        if params[2] >= params[3]:  # MACD fast period should be less than slow period
            continue
        profit = backtest(df, *params)
        if profit > best_profit:
            best_profit = profit
            best_params = params

    return best_params, best_profit

try:
    tickers = ["KRW-BTC", "KRW-ETH", "KRW-XRP", "KRW-ADA", "KRW-DOGE"]
    results = {}

    for ticker in tickers:
        try:
            best_params, best_profit = optimize_parameters(ticker)
            results[ticker] = {
                'params': best_params,
                'profit': best_profit
            }
            print(f"{ticker} 최적화 완료:")
            print(f"  최적 파라미터: 심리도 기간={best_params[0]}, 심리도 임계값={best_params[1]}, MACD 빠른 기간={best_params[2]}, MACD 느린 기간={best_params[3]}, MACD 시그널 기간={best_params[4]}")
            print(f"  최고 수익률: {best_profit:.2f}%")
        except Exception as e:
            print(f"{ticker} 오류 발생: {e}")

    print("\n각 코인별 최적 파라미터 및 수익률:")
    for ticker, result in results.items():
        print(f"{ticker}:")
        print(f"  파라미터: {result['params']}")
        print(f"  수익률: {result['profit']:.2f}%")

    best_coin = max(results, key=lambda x: results[x]['profit'])
    print(f"\n전체 최고 수익률 코인: {best_coin}")
    print(f"  파라미터: {results[best_coin]['params']}")
    print(f"  수익률: {results[best_coin]['profit']:.2f}%")

except Exception as e:
    print(f"전체 프로세스 오류 발생: {e}")