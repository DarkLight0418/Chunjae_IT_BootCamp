package day13_stream.advanced;

import soo.P;

import java.util.Arrays;

public class L {  // 루핑 메소드( forEach(), peek() )
    int is[] = {1, 2, 3, 4};
    void out1() {
        Arrays.stream(is)
                .filter(i -> i%2 == 0)
                .peek(n -> P.pln("n: " + n));  // 중간 처리 메소드
    }
    void out2() {
        Arrays.stream(is)
                .filter(i -> i%2 == 0)
                .forEach(n -> P.pln("n: " + n));  // 최종 처리 메소드
    }

    void out3() {
        int sum =  Arrays.stream(is)
                .filter(i -> i%2 == 0)
                .peek(n -> P.pln("n: " + n))
                .sum();
        P.pln("짝수합: " +  sum);
    }

    public static void main(String[] args) {
        L l = new L();
//        l.out1();
//        l.out2();
        l.out3();
    }
}
