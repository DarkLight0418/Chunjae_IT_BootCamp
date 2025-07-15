import java.util.Scanner;

class Cal {
    Scanner sc = new Scanner(System.in);

    int sum(int i, int j) {
        return i + j;
    }
    double div() {
        try {
            System.out.print("나눠지는 수: ");
            int i = sc.nextInt();
            System.out.print("나누는 수: ");
            int j = sc.nextInt();
            return (double) i / j;
        } catch (ArithmeticException ae) {
            System.out.println("0으로 나눌 수 없습니다!!");
            return div(); 
        }
    }
}

public class OhYeah {
    public static void main(String[] args) {
    D2 d = new D2();
    double r = d.div();
    System.out.println("결과 : " + r);
    //System.out.println("합: " + r);
    }
}
