package cond;

public class Switch2 {
    public static void main(String[] args) {
        int grade = 5;

        int coupone;
        switch (grade) {
            case 1:
                coupone = 1000;
                break;
            case 2:
                coupone = 2000;
                break;
            case 3:
                coupone = 3000;
                break;
            default:
                coupone = 500;
        }
        System.out.println("발급받은 쿠폰 : " + coupone);
    }
}
