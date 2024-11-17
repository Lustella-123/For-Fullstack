public class Internet {
    boolean isDownloaded = false;

    public void downloadInternet() {
        // 다운로드 되었다면 실행 안함
        if (isDownloaded == false) {
            System.out.print("인터넷 다운로드");
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

    public void useInternet() {
        System.out.println("=== 계산기 사용 시작 ===");
    }

    public void stopInternet() {
        System.out.println("=== 계산기 사용 종료 ===");
        System.out.println("");
    }

}