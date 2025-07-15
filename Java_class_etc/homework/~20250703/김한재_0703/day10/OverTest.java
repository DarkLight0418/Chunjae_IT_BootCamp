package day10;

class OverloadingTest {
    void cat() {
        System.out.println("매개변수 없음");
    }

    void cat(int a, int b) {
        System.out.println("매개변수 : " + a + ", " + b);
    }

    void cat(String c) {
        System.out.println("매개변수 : " + c);
    }
}

public class OverTest {
    public static void main(String[] args) {
        OverloadingTest ot = new OverloadingTest();
        ot.cat();
        ot.cat(20, 80);
        ot.cat("오버로딩 예제입니다.");
    }
}