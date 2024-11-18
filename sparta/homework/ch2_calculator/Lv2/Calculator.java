package Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    // 연산 결과 저장
    private int num1 = 0;
    private int num2 = 0;
    String operator = "";
    private double answer = 0;
    private Queue<Double> data = new LinkedList<>();

    // 연산 수행
    public void calculate(String operator, int num1, int num2) {
        this.operator = operator;
        this.num1 = num1;
        this.num2 = num2;
        if (operator.equals("+")) {
            plus();
        } else if (operator.equals("-")) {
            minus();
        } else if (operator.equals("*")) {
            mul();
        } else {
            div();
        }
    }

    private void plus() {
        answer = num1 + num2;
    }

    private void minus() {
        answer = num1 - num2;
    }

    private void mul() {
        answer = num1 * num2;
    }

    private void div() {
        answer = num1 / num2;
    }

    // 연산 출력
    public void getAnswer() {
        if (data.isEmpty()) { // 큐 데이터
            System.out.println("결과:" + answer);
            data.add(answer); // 비어있으면 값 추가함
        } else {
            data.remove(); // 비어있지 않으면 값 제거함
        }
    }
}
