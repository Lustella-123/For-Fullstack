package ch2_calculator.Lv3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticCalculator<T extends Number> {
    private T answer;
    private final Queue<T> data = new LinkedList<>();

    // 연산 수행
    public void calculate(OperatorType operator, T num1, T num2) {
        double val1 = num1.doubleValue();  // Number 타입을 double로 변환
        double val2 = num2.doubleValue();  // Number 타입을 double로 변환

        try {
            double result = operator.apply(val1, val2); // OperatorType을 통해 연산 수행
            answer = (T) Double.valueOf(result); // 제네릭 타입으로 변환
            data.add(answer); // 결과를 큐에 추가
        } catch (ArithmeticException e) {
            System.out.println("오류: " + e.getMessage());
            answer = null; // 예외 발생 시 결과를 null로 설정
        }
    }

    // 연산 출력 및 관리
    public void getAnswerAndManage() {
        if (answer == null) {
            System.out.println("계산 결과가 없습니다.");
        } else {
            System.out.println("결과: " + answer);
        }

        System.out.print("기준값을 입력해주세요: ");
        double thresholdValue = new java.util.Scanner(System.in).nextDouble();

        // 조건에 맞는 값들을 오름차순으로 정렬하여 출력
        List<T> greaterResults = data.stream()
                .filter(result -> result.doubleValue() > thresholdValue)
                .sorted(Comparator.comparingDouble(Number::doubleValue))
                .toList();

        if (!greaterResults.isEmpty()) {
            System.out.println("조건에 맞는 값들 (오름차순): " + greaterResults);

            // 가장 마지막 값 제거 여부 확인
            System.out.print("가장 마지막 값을 제거하시겠습니까? (y/n): ");
            String input = new java.util.Scanner(System.in).nextLine();
            if (input.equalsIgnoreCase("y")) {
                T lastValue = greaterResults.getLast();
                data.remove(lastValue);
                System.out.println("제거된 값: " + lastValue);
            }
        } else {
            System.out.println("조건에 맞는 값이 없습니다.");
        }
    }
}
