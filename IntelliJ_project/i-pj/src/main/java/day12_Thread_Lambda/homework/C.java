package day12_Thread_Lambda.homework;

public class C extends Thread {
    C() {
        setPriority(MAX_PRIORITY);  // 5 -> 10
        start();
    }

    void m1() {
        System.out.println("max: " + Thread.MAX_PRIORITY);
        System.out.println("min: " + Thread.MIN_PRIORITY);
        System.out.println("nor: " + Thread.NORM_PRIORITY);
    }
    void m2() {
        for(int i=0; i<1000; i++) {
            System.out.println("main i: " + i);
        }
    }
    public void run() {
        for(int i=0; i<1000; i++) {
            System.out.println("baby i: " + i);
        }
    }
    public static void main(String args[]) {
        C c = new C();
        c.m1();
        //c.m2();
    }
}
