package day13_stream.advanced;

import soo.P;

import java.util.Arrays;

public class M {
    int is[] = {1, 2, 3, 4};

    void out1() {
        boolean b = Arrays.stream(is).allMatch(i -> i%2==0);
        P.pln("모두 짝수인가? : " + b);
    }
    void out2() {
        boolean b = Arrays.stream(is).anyMatch(i -> i%3==0);
        P.pln("어느 하나라도 3의 배수가 존재하는가? : " + b);
    }
    void out3() {
        boolean b = Arrays.stream(is).noneMatch(i -> i%3==0);
        P.pln("3의 배수가 전혀 없는가? : " + b);
    }

    public static void main(String[] args) {
        M m = new M();
        m.out1();
        m.out2();
        m.out3();
    }
}
