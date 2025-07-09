package day12_Thread_Lambda;

import soo.P;

public class A  {
    A() {
        Thread th1 = new AA();
        th1.start();
        Thread th2 = new AA();
        th2.start();
    }
    void m1() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie) {}
            P.pln("일1");
        }
    }
    public static void main(String[] args) {
        A a = new A();
        a.m1();
    }
}
class AA extends Thread {
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie) {}
            P.pln("일2");
        }
    }
}
