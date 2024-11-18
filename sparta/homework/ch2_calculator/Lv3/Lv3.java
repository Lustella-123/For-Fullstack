package ch2_calculator.Lv3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lv3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArithmeticCalculator<Number> calculator = new ArithmeticCalculator<>();
        while (true) {
            int num1 = 0;
            int num2 = 0;
            boolean validInput = false;

            // 양의 정수 입력받기
            while (!validInput) {
                try {
                    System.out.print("양의 정수를 입력해주세요(1/2):");
                    num1 = scanner.nextInt(); // 첫 번째 정수 입력
                    if (num1 < 0) {
                        System.out.println("양의 정수를 입력해야 합니다. 다시 입력해주세요.");
                    } else {
                        validInput = true; // 유효한 입력
                    }
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 정수를 입력해주세요.");
                    scanner.next();
                }
            }
            validInput = false; // 루프용 boolean 초기화
            while (!validInput) {
                try {
                    System.out.print("양의 정수를 입력해주세요(2/2):");
                    num2 = scanner.nextInt(); // 두 번째 정수 입력
                    if (num2 < 0) {
                        System.out.println("양의 정수를 입력해야 합니다. 다시 입력해주세요.");
                    } else {
                        validInput = true; // 유효한 입력
                    }
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 정수를 입력해주세요.");
                    scanner.next();
                }
            }
            validInput = false; // 루프용 boolean 초기화
            scanner.nextLine(); // 남아있는 줄바꿈 제거

            // 사칙연산 기호 입력받기
            while (!validInput) {
                System.out.print("사칙연산 기호를 입력해주세요(+-*/):");
                String operatorInput = scanner.nextLine();
                try {
                    OperatorType operator = OperatorType.fromSymbol(operatorInput);
                    calculator.calculate(operator, num1, num2);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("잘못된 입력입니다. 올바른 사칙연산 기호를 입력해주세요.");
                }
            }

            // 결과 출력 및 저장값 관리
            calculator.getAnswerAndManage();

            // exit 입력시 루프 탈출
            System.out.print("계속 하시겠습니까?(종료:exit):");
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("exit")) { // 대소문자 구분 없이 처리
                System.out.println("프로그램을 종료합니다. 감사합니다!");
                break;
            }
        }
        scanner.close();
    }
}
