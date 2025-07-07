package day11;

public interface A {
    void mmm();
    public default void m2() {
        System.out.println("m22()");
    }
}

interface InnerA1 {
    void mmm();
    public default void m2() {
        System.out.println("m22()");
    }
}