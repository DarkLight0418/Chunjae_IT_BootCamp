package day07_20250630;

import soo.P;

public class C {
    byte bs[] = new byte[2];
    char cs[] = new char[2];
    int is[] = new int[2];
    double ds[] = new double[2];
    boolean flags[] = new boolean[2];
    A as = new A();  // 왜 new A[2] 형태로 되어 있었지????
    
    void m1() {
        P.pln("bs[0] " + bs[0]);
    }
}
