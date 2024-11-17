public class Youtube {
    boolean isDownloaded = false;

    public void downloadYoutube() {
        // 다운로드 되었다면 실행 안함
        if (isDownloaded == false) {
            System.out.print("유튜브 다운로드");
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

    public void useYoutube() {
        System.out.println("=== 유튜브 사용 시작 ===");
    }

    public void stopYoutube() {
        System.out.println("=== 유튜브 사용 종료 ===");
        System.out.println("");
    }
}