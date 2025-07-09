package day12_Thread_Lambda;

import soo.P;

class DTh extends Thread {
    public void run() {
        D.m();
    }
}

public class D {
    static int i;  // 무결성을 깨버림
    static int k;
    D() {
        Thread th1 = new DTh();
        th1.start();  // JVM -> new 제어 -> run()

        Thread th2 = new DTh();
        th2.start();  //
        try {
            th1.join();
            th2.join();  // 계속 함께 하는 거래요
        } catch (InterruptedException ie) {}
        
        P.pln("i: " + i);  // main()
    }

    synchronized static void m() {  // synchronized
        for(int j=0; j<10000; j++) {
            k++;
            i++;
        }
    }
    public static void main(String[] args) {
        new D();
    }
}
