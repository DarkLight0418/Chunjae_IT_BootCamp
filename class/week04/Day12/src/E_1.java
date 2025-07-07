package day12;

import soo.P;

interface EI1_1{
    void m1();
}
class EI1Child2 implements EI1_1{
    public void m1() {
        P.pln("m1() 구현1");
    }
}
interface EI2_2 {
    void m2(int i);
}
interface EI3_2{
    int m3(int i, int j);
}
class E_1 {
    void use1() {
        EI1_1 ei1_1 = new EI1Child2(); //1
        EI1_1 ei1_2 = new EI1_1() { //2
            public void m1() {
                P.pln("m1() 구현2");
            }
        };
        EI1_1 ei1_3 = () -> P.pln("m1() 구현3");//3

        ei1_1.m1();
        ei1_2.m1();
        ei1_3.m1();
    }
    void use2() {
        EI2_2 ei2 = (i) -> {
            int r = i + 20;
            P.pln("m2()구현 r: " + r);
        };

        ei2.m2(10);
    }
    void use3() {
        //EI3 ei3 = (i, j) -> { return i+j; };
        EI3_2 ei3 = (i, j) -> i+j;

        int sum = ei3.m3(1,2);
        P.pln("sum: " + sum);
    }
    void use4() {
        //EI3 ei3 = (i, j) -> multiply(i, j);
        EI3_2 ei3 = (i, j) -> new EOther2().multiply2(i, j);

        int mul = ei3.m3(1, 2);
        P.pln("mul: " + mul);
    }
    int multiply(int i, int j) {
        i = i + 1;
        return i*j;
    }
    public static void main(String args[]) {
        E_1 e = new E_1();
        //e.use1();
        //e.use2();
        //e.use3();
        e.use4();
    }
}
class EOther2 {
    int multiply2(int i, int j) {
        i = i + 1;
        return i*j;
    }
}
