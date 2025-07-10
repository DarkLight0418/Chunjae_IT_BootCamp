package day11_Interface.homework;

// 추상 인터페이스 선언 (public 제한자 사용)
public abstract interface A1_CommentVer {
    // 상수 선언 - 인터페이스의 모든 변수는 자동으로 public static final이 됨
    public static final int I = 2;

    // 추상 메소드 선언 - 구현부가 없고 자식 클래스에서 반드시 구현해야 함
    public abstract void m1();

    // 디폴트 메소드 - Java 8부터 도입된 기능으로 인터페이스 내에서 구현부를 가질 수 있음
    public default void m2() {
        System.out.println("m2()");
    }

    // 정적 메소드 - Java 8부터 도입된 기능으로 인터페이스 내에서 구현 가능한 정적 메소드
    public static void m3() {
        System.out.println("m3()");
    }
}

// 추상 인터페이스 선언 (abstract 제한자만 사용 - public 생략)
abstract interface A1_1HmCm {
    // 상수 선언
    public static final int I = 2;

    // 추상 메소드 선언
    public abstract void m1();

    // 디폴트 메소드
    public default void m2() {
        System.out.println("m2()");
    }

    // 정적 메소드
    public static void m3() {
        System.out.println("m3()");
    }
}