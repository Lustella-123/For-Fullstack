package Lv4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Menu 객체 생성하면서 카테고리 이름 설정
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("Burgers"));
        menus.add(new Menu("Drinks"));
        menus.add(new Menu("Desserts"));

        // 각 Menu에 해당하는 항목 추가
        menus.get(0).addMenuItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menus.get(0).addMenuItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menus.get(0).addMenuItem(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menus.get(0).addMenuItem(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        menus.get(1).addMenuItem(new MenuItem("Coke", 1.5, "콜라"));
        menus.get(1).addMenuItem(new MenuItem("Sprite", 1.5, "스프라이트"));

        menus.get(2).addMenuItem(new MenuItem("Ice Cream", 3.0, "바닐라 아이스크림"));
        menus.get(2).addMenuItem(new MenuItem("Brownie", 4.5, "초콜릿 브라우니"));

        // Kiosk 객체 생성 및 시작
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }
}
