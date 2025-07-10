package day08_Collection.homework;

import java.util.ArrayList;
import java.util.TreeSet;

public class B2_homework {
    TreeSet<String> ts = new TreeSet<String>();

    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        for(String item: ts) {
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B2_homework b1 = new B2_homework();
        b1.in();
        b1.out();
    }
}

class B2_homework2 {
    TreeSet<String> ts = new TreeSet<>();

    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        for(String item: ts) {
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B2_homework2 b2 = new B2_homework2();
        b2.in();
        b2.out();
    }
}

