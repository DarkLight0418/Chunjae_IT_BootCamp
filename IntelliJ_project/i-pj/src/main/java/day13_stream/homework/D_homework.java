package day13_stream.homework;

import soo.P;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class D_homework {
    String strs[] = {"봄", "여름", "가을", "겨울"};

    void out1() {
        Stream<String> s = Arrays.stream(strs);
        s.forEach(item -> {
            P.p(item + " by ");
            P.pln(Thread.currentThread().getName());
        });
    }

    List<String> toList() {
        List<String> list = Arrays.asList(strs);
        // List<String> list = Arrays.asList("봄", "여름", "가을", "겨울");
        return list;
    }

    void out2() {
        List<String> list = toList();
        Stream<String> s = list.stream();
        s.forEach( item -> {
            System.out.print(item + " by ");
            System.out.println(Thread.currentThread().getName());
        });
    }


    public static void main(String[] args) {
        D_homework d1 = new D_homework();
        d1.out2();
    }
}
