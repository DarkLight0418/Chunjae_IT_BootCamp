package day11.t;

// day11 패키지에 있는 A, A1, A2 인터페이스를 가져옴
import day11.A1;
import day11.A2;
import day11.A;

// A 인터페이스를 구현한 클래스
class AChild2 implements A {

    // A 인터페이스에서 요구한 m1() 구현
    public void m1() {
        System.out.println("m1()");
    }

    // A2 인터페이스에서 요구한 mm() 구현
    public void mm() {
        System.out.println("mm()");
    }

    // A2 인터페이스에서 요구한 mmm() 구현
    public void mmm() {
        System.out.println("mmm()");
    }
}

// 실행 클래스
class AUser2 {
    public static void main(String args[]) {
        // AChild 객체 생성
        AChild2 child = new AChild2();

        // ------------------------------
        // AChild → A 타입으로 자동 형변환 (업캐스팅)
        // A는 A1, A2를 상속하는 상위 인터페이스로 추정됨
        // 따라서 A 타입 참조 변수로 A1, A2의 메서드도 호출 가능함
        A a = child;
        System.out.println("a.I: " + a.I); // A 인터페이스에 정의된 상수 I
        a.m1();  // A 인터페이스의 메서드
        a.mm();  // A2 인터페이스의 메서드
        a.mmm(); // A2 인터페이스의 메서드
        a.m2();  // A1 인터페이스의 메서드
        System.out.println();

        // ------------------------------
        // A → A1 인터페이스로 업캐스팅
        // child가 AChild이고 A1을 구현했기 때문에 가능
        A1 a1 = a;
        System.out.println("a1.I: " + a1.I); // A1 인터페이스에 정의된 상수 I
        a1.m1(); // A1 인터페이스 메서드
        a1.m2(); // A1 인터페이스 메서드
        A1.m3(); // A1 인터페이스의 static 메서드 호출
        System.out.println();

        // ------------------------------
        // A → A2 인터페이스로 업캐스팅
        // 마찬가지로 AChild가 A2를 구현했기 때문에 형변환 가능
        A2 a2 = a;
        a2.mm();  // A2 인터페이스의 메서드 호출
    }
}
