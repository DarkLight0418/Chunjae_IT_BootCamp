import java.util.Scanner;

class D1 {
    int sum(int i, int j) {  // 캡슐화 : 메소드 타입이랑 매개변수 뭐 있나 정도만
        return i+j;
    }
}

class DUser1 {
    public static void main(String[] args) {
        D1 d = new D1();
        int r = d.sum(100, 400);
        System.out.println("합: " +  r);
    }
}

class D2 {
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

class DUser2 {
    public static void main(String[] args) {
        D2 d = new D2();
        double r = d.div();
        System.out.println("결과 : " + r);
        //System.out.println("합: " + r);
    }
}