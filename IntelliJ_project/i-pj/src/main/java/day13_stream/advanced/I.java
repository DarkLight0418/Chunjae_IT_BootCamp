package day13_stream.advanced;

import soo.P;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class I {
    List<String> list1;
    List<String> list2;

    void in() {
        list1 = Arrays.asList("오늘 수업 어찌어찌 따라는 가겠는데", "오늘 너무 힘들다...");
        list2 = Arrays.asList("1, 2, 3", "4, 5");
    }

    void showLists() {
        list1.stream().forEach(data -> P.pln(data));
        P.pln("");
        list2.stream().forEach(data -> P.pln(data));
    }

    void out1() {
//        Stream<String> ss = list1.stream().flatMap(data -> Arrays.stream(data.split(" ")));
//        ss.forEach(word -> P.pln(word));

        list1.stream()
                .flatMap(data -> Arrays.stream(data.split(" ")))
                .forEach(word -> P.pln(word));

    }

    void out2() {
        IntStream iss = list2.stream().flatMapToInt(data -> {
            String strs[] = data.split(",");
            int is[] = new int[strs.length];
            for (int i = 0; i < is.length; i++) {
                is[i] = Integer.parseInt(strs[i].trim());
            }
            return Arrays.stream(is);
        });
        iss.forEach(num -> P.pln(num));
    }

    void out22() {
        list2
                .stream()
                .flatMapToInt(data -> {
                    String strs[] = data.split(",");
                    int is[] = new int[strs.length];
                    for (int i = 0; i < is.length; i++) {
                        is[i] = Integer.parseInt(strs[i].trim());
                    }
                    return Arrays.stream(is);
                })
                .forEach(P::pln);
    }

    public static void main(String args[]) {
        I i = new I();
        i.in();
        //i.showLists();
        //i.out1();
        //i.out2();
        i.out22();
    }
}

