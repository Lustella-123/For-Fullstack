package homework.fourweek;

public class Main {
    public static void main(String[] args) {
        boolean calculateEnded = false;
        // 구현 2.
        while(!calculateEnded) {
            try {
                CalculatorApp.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}