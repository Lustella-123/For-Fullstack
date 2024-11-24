package Lv4;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        // 스캐너 선언
        Scanner scanner = new Scanner(System.in);

        // 반복문 시작
        while (true) {
            // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
            System.out.println("[ MAIN MENU ]");
            for (int i = 0; i < menus.size(); i++) {
                System.out.println((i + 1) + ". " + menus.get(i).getCategoryName());
            }
            System.out.println("0. 종료");

            // 숫자 입력 받기
            int choice = scanner.nextInt();

            // 입력 받은 숫자가 올바르다면 인덱스를 활용하여 List에 접근하기
            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (choice > 0 && choice <= menus.size()) {
                menus.get(choice - 1).displayMenu();
                // 숫자 입력 받기
                int subChoice = scanner.nextInt();

                // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 Menu가 가지고 있는 List<MenuItem>에 접근하기
                if (subChoice == 0) {
                    continue;
                } else if (subChoice > 0 && subChoice <= menus.get(choice - 1).getMenuItems().size()) {
                    MenuItem selectedItem = menus.get(choice - 1).getMenuItems().get(subChoice - 1);
                    System.out.println("선택한 메뉴: " + selectedItem.name + " | W " + selectedItem.price + " | " + selectedItem.description);
                } else {
                    System.out.println("잘못된 선택지입니다.");
                }
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
