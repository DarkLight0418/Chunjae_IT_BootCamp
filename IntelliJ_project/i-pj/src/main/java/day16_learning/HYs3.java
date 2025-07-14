package day16_learning;

public class HYs3 {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;

        int x = a++ + ++b * c++;
        int y = ++a + b++ + ++c;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}
