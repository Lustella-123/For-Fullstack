package homework.fourweek;

import java.util.regex.Pattern;

public class Parser {
    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^[0-9]*$";

    private final Calculator calculator = new Calculator();

    public void parseFirstNum(String firstInput) {
        // 구현 1.
        while(true) {
            try {
                boolean pattern = Pattern.matches(NUMBER_REG, firstInput);
                if (!pattern) {
                    throw new BadInputException(NUMBER_REG);
                }
                break;
            } catch (BadInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void parseSecondNum(String secondInput) {
        // 구현 1.
        while(true) {
        try {
            boolean pattern = Pattern.matches(NUMBER_REG, secondInput);
            if (!pattern) {
                throw new BadInputException(NUMBER_REG);
            }
            break;
        } catch (BadInputException e) {
            System.out.println(e.getMessage());
        }
        }
    }

    public void parseOperator(String operationInput) {
        try {
            boolean pattern = Pattern.matches(OPERATION_REG, operationInput);
            if (!pattern) {
                throw new BadInputException(OPERATION_REG);
            }
        } catch (BadInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public double executeCalculator() {
        return calculator.calculate();
    }
}