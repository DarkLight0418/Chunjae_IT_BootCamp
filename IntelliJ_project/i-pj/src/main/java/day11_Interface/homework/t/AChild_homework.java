package day11_Interface.homework.t;

import day11_Interface.homework.A;

public class AChild_homework implements A {
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
        System.out.println("mmm");
    }

    public static void main(String[] args) {

    }
}
