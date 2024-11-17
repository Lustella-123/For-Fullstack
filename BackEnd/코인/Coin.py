import numpy as np
import pyupbit
import time

# 지수 이동 평균(EMA) 계산 함수
def calculate_ema(prices, period):
    ema = [np.mean(prices[:period])]  # 첫 번째 EMA는 단순 이동 평균으로 시작
    multiplier = 2 / (period + 1)
    for price in prices[period:]:
        ema.append((price - ema[-1]) * multiplier + ema[-1])
    return np.array(ema)

# MACD 계산 함수
def calculate_macd(prices):
    fast_ema = calculate_ema(prices, 12)
    slow_ema = calculate_ema(prices, 26)
    macd = fast_ema[len(fast_ema) - len(slow_ema):] - slow_ema
    signal = calculate_ema(macd, 9)
    return macd, signal

def calculate_rsi(prices, period=14):
    delta = np.diff(prices)
    gain = np.where(delta > 0, delta, 0)
    loss = np.where(delta < 0, -delta, 0)

    avg_gain = np.zeros_like(prices)
    avg_loss = np.zeros_like(prices)

    avg_gain[period] = np.mean(gain[:period])
    avg_loss[period] = np.mean(loss[:period])

    for i in range(period + 1, len(prices)):
        avg_gain[i] = (avg_gain[i - 1] * (period - 1) + gain[i - 1]) / period
        avg_loss[i] = (avg_loss[i - 1] * (period - 1) + loss[i - 1]) / period

    rs = np.where(avg_loss != 0, avg_gain / avg_loss, 0)  # 0으로 나누기 방지
    rsi = 100 - (100 / (1 + rs))
    return rsi

def check_signals(macd, signal, rsi):
    buy_signal = False
    sell_signal = False

    # 매수 조건: MACD가 시그널을 상향 돌파하고, RSI가 30 이하일 때
    if macd[-1] > signal[-1] and rsi[-1] < 30:
        buy_signal = True
    # 매도 조건: MACD가 시그널을 하향 돌파하고, RSI가 70 이상일 때
    elif macd[-1] < signal[-1] and rsi[-1] > 70:
        sell_signal = True

    return buy_signal, sell_signal

# API 키 설정 (실제 키로 교체해야 합니다)
access_key = '8U7eN8taXcRE1eYBMvvl6f035fnbM9GDLBjLBvTY'
secret_key = 'Tq1CmkQoMGviSFbPOwmBIdLrfsxjKuyrQEs9uS2k'

try:
    upbit = pyupbit.Upbit(access_key, secret_key)
    print("Upbit API에 성공적으로 연결되었습니다.")
except Exception as e:
    print(f"Upbit API 연결 오류: {e}")
    exit(1)

# 매수 주문 함수
def buy_market_order(ticker, amount):
    """시장가 매수 주문"""
    upbit.buy_market_order(ticker, amount)

# 매도 주문 함수
def sell_market_order(ticker, amount):
    """시장가 매도 주문"""
    upbit.sell_market_order(ticker, amount)

# 자동 매매 함수
def execute_trade(ticker):
    try:
        df = pyupbit.get_ohlcv(ticker, interval="minute1", count=100)
        if df is None or df.empty:
            print(f"{ticker} 데이터를 가져오는 데 실패했습니다.")
            return

        close_prices = df['close'].values
        rsi = calculate_rsi(close_prices)

        current_price = pyupbit.get_current_price(ticker)
        balance = upbit.get_balance(ticker)

        if rsi[-1] < 30:
            krw_balance = upbit.get_balance("KRW")
            if krw_balance > 5000:  # 최소 주문 금액
                upbit.buy_market_order(ticker, krw_balance * 0.1)
                print(f'매수: {ticker}, 가격: {current_price}')
        elif rsi[-1] > 70 and balance > 0:
            upbit.sell_market_order(ticker, balance)
            print(f'매도: {ticker}, 가격: {current_price}')
        else:
            print(f'현재 RSI: {rsi[-1]:.2f}, 거래 없음')

    except Exception as e:
        print(f"거래 실행 중 오류 발생: {e}")

# 메인 루프
print("프로그램이 시작되었습니다. 1분마다 거래를 확인합니다.")
while True:
    try:
        execute_trade("KRW-BTC")  # 비트코인 거래
        time.sleep(60)  # 1분 대기
    except Exception as e:
        print(f"메인 루프 오류: {e}")
        time.sleep(60)  # 오류 발생 시에도 1분 대기
