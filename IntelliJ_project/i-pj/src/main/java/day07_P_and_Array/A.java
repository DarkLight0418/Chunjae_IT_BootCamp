package day07_P_and_Array;

import soo.P;

public class A {
    void m1() {
        for(int i=0; i<5; i++) {
            P.pln("i" + i);
        }
    }

    public static void main(String[] args) {
        A a = new A();
        a.m1();
    }
}
