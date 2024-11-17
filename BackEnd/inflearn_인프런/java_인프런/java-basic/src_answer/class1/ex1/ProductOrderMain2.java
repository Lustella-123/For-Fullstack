package class1.ex1;

import com.helloshop.product.Product;

public class ProductOrderMain2 {
    public static void main(String[] args) {
        ProductOrder[] orders = new ProductOrder[3];

        ProductOrder d = new ProductOrder();
        d.productName = "두부";
        d.price = 2000;
        d.quantity = 2;
        orders[0] = d;

         ProductOrder k = new ProductOrder();
        k.productName = "김치";
        k.price = 5000;
        k.quantity = 1;
        orders[1] = k;

        ProductOrder c = new ProductOrder();
        c.productName = "콜라";
        c.price = 1500;
        c.quantity = 2;
        orders[2] = c;

        int totalPrice = d.price * d.quantity + k.price * k.quantity + c.price * c.quantity;

        for (ProductOrder order : orders) {
            System.out.println("상품명: " +order.productName + ", 가격: " + order.price + ", 수량: " + order.quantity);
        }
        System.out.println("총 결제 금액: " + totalPrice);
    }
}
