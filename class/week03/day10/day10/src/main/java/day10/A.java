package day10;

public class A {
    public int i;

    public void m() {
        System.out.println("m()");
    }
}

class Auser {
    public static void main(String[] args) {
        A a1 = new A();
        a1.m();
    }
}


