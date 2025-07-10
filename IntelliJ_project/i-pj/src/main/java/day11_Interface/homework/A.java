package day11_Interface.homework;

public interface A {
    final int I = 2;
    void mmm();

    public default void m2() {
        System.out.println("m2_2()");
    }

    void m1();
    void mm();
}

interface InnerA1 {
    void mmm();
    public default void m2() {
        System.out.println("m2_3()");
    }
}
