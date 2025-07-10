package day10_extends_implements.homework;

abstract class E_homework {
    int i;
    E_homework() {}
    void m1() {
        System.out.println("m1()");
    }
    abstract void m2();
}

class EChild1 extends E_homework {
    void m2() {
        System.out.println("siuuuuu");
    }
}

class EUser1 {
    public static void main(String[] args) {
        E_homework e1 = new EChild1();
        System.out.println(e1.i);
        e1.m1();
        e1.m2();
    }
}
abstract class E_homework2 {
    int i;
    E_homework2() {}
    void m1() {
        System.out.println("m1()");
    }
    abstract void m2();
}

class EChild2 extends E_homework2 {
    void m2() {
        System.out.println("siuuuuuu");
    }
}

class EUser2 {
    public static void main(String[] args) {
        E_homework2 e2 = new EChild2();
        System.out.println(e2.i);
        e2.m1();
        e2.m2();
    }
}

abstract class E_homework3 {
    int i;
    E_homework3 () {}
    void m1() {
        System.out.println("m1()");
    }
    abstract void m2();
}

class EChild3 extends E_homework3 {
    void m2() {
        System.out.println("siuuuuu");
    }
}

class EUser3 {
    public static void main(String[] args) {
        E_homework3 e3 = new EChild3();
        System.out.println(e3.i);
        e3.m1();
        e3.m2();
    }
}