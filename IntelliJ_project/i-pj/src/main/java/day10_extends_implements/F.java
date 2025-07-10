package day10_extends_implements;

public class F {
    String name;
    F() {
    }

    public static void main(String[] args) {
        F f = new F();
        System.out.println("");
    }

    void m() {
        System.out.println("m()");
    }
}

class Helper {
    F f;
    Helper(F f) {
        this.f = f;
    }

    void work() {
        //f.m();
    }
}