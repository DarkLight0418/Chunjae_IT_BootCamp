package day13.advanced;

import soo.P;

import java.util.Arrays;

public class N {
    int is[] = {1, 2, 3, 4, 5, 6};

    void out1() {
        long count = Arrays.stream(is).filter(i -> i%2 == 0).count();
        P.pln("짝수 갯수: " + count);
    }
    void out2() {
        long sum = Arrays.stream(is).filter(i -> i%2==0).sum();
        P.pln("짝수 합: " + sum);
    }
    void out3() {
        long avg = (long) Arrays.stream(is).filter(i -> i%2==0).average().getAsDouble();
        P.pln("짝수 평균 : " + avg);
    }
    void out4() {
        int max = Arrays.stream(is).filter(i -> i%2==0).max().getAsInt();
        P.pln("짝수 최대 : " + max);
    }
    void out5() {
        int min = Arrays.stream(is).filter(i -> i%2==0).min().getAsInt();
        P.pln("짝수 최소 : " + min);
    }
    void out6() {
        long first = Arrays.stream(is).filter(i -> i%2==0).findFirst().getAsInt();
        P.pln("짝수 처음 : " + first);
    }

    public static void main(String[] args) {
        N n = new N();
        n.out1();n.out2();n.out3();n.out4();n.out5();n.out6();
    }
}
