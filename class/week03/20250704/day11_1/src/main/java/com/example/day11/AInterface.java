package com.example.day11;

public abstract interface AInterface {
    public static final int I = 1; // 멤버 변수는 public static final이 생략되어 있음
    void m1();  // abstract void m();
    public default void m2() {}
    public static void m3() {}
}

