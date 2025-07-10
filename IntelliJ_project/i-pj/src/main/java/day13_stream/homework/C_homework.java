package day13_stream.homework;

import day13_stream.basic.C;
import soo.P;

import java.util.Hashtable;
import java.util.Set;
import java.util.stream.Stream;

public class C_homework {
    Hashtable<Integer, String> ht = new Hashtable<>();

    void in() {
        ht.put(1, "봄"); ht.put(2, "여름"); ht.put(3, "가을"); ht.put(4, "겨울"); ht.put(5, "가을");
    }

    void out2() {
        Set<Integer> keys = ht.keySet();
        Stream<Integer> s = keys.stream();
        s.forEach(key -> {
            System.out.print("key: " + key + ", value: " + ht.get(key) + " by ");
            System.out.println(Thread.currentThread().getName());
        });
    }


    public static void main(String[] args) {
        C_homework c1 = new C_homework();
        c1.in();
        c1.out2();
    }
}

class C_homework2 {
    Hashtable<Integer, String> ht = new Hashtable<>();

    void in() {
        ht.put(1, "봄"); ht.put(2, "여름"); ht.put(3, "가을"); ht.put(4, "겨울"); ht.put(5, "가을");
    }
    void out1() {
        Set<Integer> keys = ht.keySet();
        Stream<Integer> ps = keys.parallelStream();
        ps.forEach(key -> {
            P.p("key: " + key + ", value: " + ht.get(key) + " by ");
            P.pln(Thread.currentThread().getName());
        });
    }
    void out2() {
        Set<Integer> keys = ht.keySet();
        Stream<Integer> ps = keys.parallelStream();
        ps.forEach(key -> {
            System.out.print("key: " + key + ", value: " + ht.get(key) + " by ");
            System.out.println(Thread.currentThread().getName());
        });
    }


    public static void main(String[] args) {
        C_homework2 c2 = new C_homework2();
        c2.in();
        c2.out1();
        c2.out2();
    }
}
