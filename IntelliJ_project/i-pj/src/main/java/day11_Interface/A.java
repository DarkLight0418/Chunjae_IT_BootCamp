package day11_Interface;

public interface A {
    final int I=2;
    void mmm();
    public default void m2() {
        System.out.println("m22()");
    }
    void m1();

    void mm();
}

interface InnerA1 {
    void mmm();
    public default void m2() {
        System.out.println("m22()");
    }
}