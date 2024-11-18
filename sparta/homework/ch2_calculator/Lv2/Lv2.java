package Lv2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lv2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
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
                String operator = scanner.nextLine();
                // 확인 후 연산하기
                if (operator.equals("+") || operator.equals("-") || operator.equals("*")) {
                    calculator.calculate(operator, num1, num2); // calculator 클래스 활용
                    validInput = true;
                    break;
                } else if (operator.equals("/")) {
                    if (num2 == 0) {
                        System.out.println("0으로 나눌 수 없습니다. 다시 입력해주세요.");
                    } else {
                        calculator.calculate(operator, num1, num2);
                        validInput = true;
                    }
                } else {
                    System.out.println("잘못된 입력입니다. 사칙연산 기호를 입력해주세요.");
                }
            }

            // 결과 출력하기
            calculator.getAnswer();

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
