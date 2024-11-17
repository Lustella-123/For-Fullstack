package static1;

public class DataCountMain1 {
    public static void main(String[] args) {
        Data1 data1 = new Data1("a");
        System.out.println("a="+data1.count);

        Data1 data2 = new Data1("b");
        System.out.println("b="+data2.count);

        Data1 data3 = new Data1("c");
        System.out.println("c="+data3.count);
    }
}
