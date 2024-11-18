package ch2_calculator.Lv3;

public enum OperatorType {
    ADD("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    SUBTRACT("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            if (y == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            return x / y;
        }
    };

    private final String symbol;

    OperatorType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    public static OperatorType fromSymbol(String symbol) {
        for (OperatorType op : values()) {
            if (op.getSymbol().equals(symbol.trim())) {
                return op;
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + symbol);
    }

}
