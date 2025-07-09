package day13.homework;

import java.util.TreeSet;
import java.util.stream.Stream;

public class B {
    TreeSet<String> ts = new TreeSet<>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("봄"); ts.add("가을"); ts.add("겨울");
    }

    void out1() {
        Stream<String> s = ts.descendingSet().stream();
        s.forEach(item -> {
            System.out.print(item + " by ");
            System.out.println(Thread.currentThread().getName());
        });
    }

    public static void main(String args[]) {
        B b = new B();
        b.in();
        b.out1();
    }
}
