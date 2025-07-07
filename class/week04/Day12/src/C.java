package day12;

import soo.P;

public class C extends Thread {
    C(){
        setPriority(MAX_PRIORITY); //5->10
        start();
    }
    void m1() {
        P.pln("max: " + Thread.MAX_PRIORITY); //10
        P.pln("min: " + Thread.MIN_PRIORITY); //1
        P.pln("nor: " + Thread.NORM_PRIORITY); //5
    }
    void m2() {
        for(int i=0; i<1000; i++) {
            P.pln("main i: " + i);
        }
    }
    public void run() {
        for(int i=0; i<1000; i++) {
            P.pln("baby i: " + i);
        }
    }
    public static void main(String args[]) {
        C c = new C();
        //c.m1();
        c.m2();
    }
}
