package day10_extends_implements.homework;

public class F_homework {
    String name;
    F_homework() {

    }

    public static void main(String[] args) {
        F_homework f1 = new F_homework();
        System.out.println("");
    }
    void m() {
        System.out.println("m()");
    }
}

class Helper {
    F_homework f1;
    Helper(F_homework f1) {
        this.f1 = f1;
    }

    void work() {
        f1.m();
    }
}