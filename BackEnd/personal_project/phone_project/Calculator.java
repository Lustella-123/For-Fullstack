public class Calculator {
    boolean isDownloaded = false;

    public void downloadCalculator() {
        // 다운로드 되었다면 실행 안함
        if (isDownloaded == false) {
            System.out.print("계산기 다운로드");
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(".");
            System.out.println("");
            isDownloaded = true;
        }
    }

    public void useCalculator() {
        System.out.println("=== 계산기 사용 시작 ===");
    }

    public void stopCalculator() {
        System.out.println("=== 계산기 사용 종료 ===");
        System.out.println("");

    }
}
