package scanner;

import java.util.Scanner;

public class ScannerEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("문자열을 입력하세요(exit:종료):");
            String str = scanner.nextLine();
            System.out.println("입력한 문자열:" + str);
            if (str.equals("exit")) {
                break;
            }
        }
    }
}
