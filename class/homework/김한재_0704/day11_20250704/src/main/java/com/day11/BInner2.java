package com.day11;

class BInner2 {
    int i2 = 2;
    void m2() {
        System.out.println("m2()");
    }
    public static void main(String args[]) {
        com.day11.B.BInner1 in1_1 = new B().new BInner1();
        System.out.println("in1_1.i1: " + in1_1.i1);
        in1_1.m_1();

        BInner2 in2_1 = new BInner2();
        System.out.println("in2_1.i2: " + in2_1.i2);
        in2_1.m2();
    }
}

