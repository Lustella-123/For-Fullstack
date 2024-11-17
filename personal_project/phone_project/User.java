import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);

    public void usePhone() {

        // 사용자 정보 입력
        String name = "";
        while (name.isBlank() || !name.matches("^[a-zA-Z가-힣]+$")) {
            System.out.print("사용자 이름 : ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("이름은 빈 칸일 수 없습니다. 다시 입력해주세요.");
            } else if (!name.matches("^[a-zA-Z가-힣]+$")) {
                System.out.println("이름에는 숫자 혹은 문자가 포함될 수 없습니다. 다시 입력해주세요.");
            }
        }

        int age = -1;
        while (age <= 0) {
            System.out.print("사용자 나이 : ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age <= 0) {
                    System.out.println("나이는 양수여야 합니다. 다시 입력해주세요.");
                }
            } else {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.next();
            }
        }

        Phone user = new Phone(name, age);

        // 사용자 기기 사용
        user.useDevice();

        // 사용자 기기 종료
        user.stopDevice();
    }
}
