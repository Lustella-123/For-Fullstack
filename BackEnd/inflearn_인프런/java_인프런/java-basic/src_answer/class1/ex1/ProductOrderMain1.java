package class1.ex1;

public class ProductOrderMain1 {
    public static void main(String[] args) {
        ProductOrder d = new ProductOrder();
        d.productName = "두부";
        d.price = 2000;
        d.quantity = 2;

         ProductOrder k = new ProductOrder();
        k.productName = "김치";
        k.price = 5000;
        k.quantity = 1;

         ProductOrder c = new ProductOrder();
        c.productName = "콜라";
        c.price = 1500;
        c.quantity = 2;

        int totalPrice = d.price * d.quantity + k.price * k.quantity + c.price * c.quantity;

        System.out.println("상품명: " + d.productName + ", 가격: " + d.price + ", 수량: " + d.quantity);
        System.out.println("상품명: " + k.productName + ", 가격: " + k.price + ", 수량: " + k.quantity);
        System.out.println("상품명: " + c.productName + ", 가격: " + c.price + ", 수량: " + c.quantity);
        System.out.println("총 결제 금액: " + totalPrice);
    }
}
