import java.util.Scanner;

public class Phone {

    // 사용자 정보
    private String name;
    private int age;

    // 사용자 정보 입력
    public Phone(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 사용자 사용 시작
    public void useDevice() {
        // 기기 사용 기능
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        // 기기 사용 대기
        for (int i = 0; i < 2; i++) {
            System.out.println(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("폰 사용을 시작합니다.");
        for (int i = 0; i < 2; i++) {
            System.out.println(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 기기 사용 시작
        System.out.println("유저 이름 : " + name);
        System.out.println("유저 나이 : " + age + "세");
        Calculator calculator = new Calculator();
        Youtube youtube = new Youtube();
        Internet internet = new Internet();
        // 기능 선택
        while (isRunning) {
            System.out.println("기능 : 1.calculator 2.youtube 3.internet 4.phoneOff");
            String function = scanner.nextLine();

            switch (function) {
                // 계산기 기능
                case "1":
                    System.out.println("");
                    calculator.downloadCalculator();
                    calculator.useCalculator();
                    calculator.stopCalculator();
                    break;
                // 유튜브 기능
                case "2":
                    System.out.println("");
                    youtube.downloadYoutube();
                    youtube.useYoutube();
                    youtube.stopYoutube();
                    break;
                // 인터넷 기능
                case "3":
                    System.out.println("");
                    internet.downloadInternet();
                    internet.useInternet();
                    internet.stopInternet();
                    break;
                case "4":
                    isRunning = false;
                    break;
                    // 잘못된 기능 선택
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    public void stopDevice() {
        System.out.println("");
        System.out.println("폰 사용을 종료합니다. 이용해 주셔서 감사합니다.");
    }
}
