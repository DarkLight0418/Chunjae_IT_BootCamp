package com.day11;
public class B {
    int i = 0;
    void m_1() {
        System.out.println("m()");
    }

    class BInner1 {
        int i1 = 1;
        void m1() {
            System.out.println("m1()");
        }
    }

    static class BInner2 {
        int i2 = 2;
        void m2() {
            System.out.println("m2()");
        }
        public static void main(String args[]) {
            B.BInner1 in1_1 = new B().new BInner1();
            System.out.println("in1_1.i1: " + in1_1.i1);
            in1_1.m1();

            B.BInner2 in2_1 = new B.BInner2();
            System.out.println("in2_1.i2: " + in2_1.i2);
            in2_1.m2();
        }
    }
}
