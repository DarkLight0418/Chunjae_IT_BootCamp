package day10_extends_implements.homework;

public class AUser_homework extends A {
    AUser_homework() {
        super();  // 부모 거 그대로 가져올게요
    }

    void use() {
        System.out.println("i: " + i);
        m();  // 부모 'A'꺼 쓸 수 있음
    }

    public static void main(String[] args) {
        new AUser_homework().use();
    }
}

class AUser_homework2 extends A {
    AUser_homework2() {
        super();
    }

    void use() {
        System.out.println("i: " + i);
        m();
    }

    public static void main(String[] args) {
        new AUser_homework2().use();
    }
}

class AUser_homework3 extends A {
    AUser_homework3() {
        super();
    }

    void use() {
        System.out.println("i: " + i);
        m();
    }

    public static void main(String[] args) {
        new AUser_homework3().use();
    }
}