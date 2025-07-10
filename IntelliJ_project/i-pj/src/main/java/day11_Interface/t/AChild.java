package day11_Interface.t;

import day11_Interface.A1;
import day11_Interface.A2;
import day11_Interface.A;

class AChild implements A{
    public void m1() {
        System.out.println("m1()");
    }
    public void mm() {
        System.out.println("mm()");
    }
    public void mmm() {
        System.out.println("mmm()");
    }
}
class AUser {
    void mmm() {
        System.out.println("");
    }

    public static void main(String args[]) {
        AChild child =  new AChild();

        A a = child; //자동형변환
        System.out.println("a.I: " + a.I);
        a.m1();
        a.mm();
        a.mmm();
        a.m2();
        System.out.println();

        A1 a1 = (A1) a; //자동형변환
        System.out.println("a1.I: " + a1.I);
        a1.m1();
        a1.m2();
        A1.m3();
        System.out.println();

        A2 a2 = (A2) a; //자동형변환
        a2.mm();
    }
}

