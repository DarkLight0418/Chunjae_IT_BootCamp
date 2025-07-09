package day08_Collection.homework;

import java.util.Iterator;
import java.util.TreeSet;

public class B1_homework {
    TreeSet<String> ts = new TreeSet<>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("봄");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        //Iterator<String> iter = ts.iterator();
        Iterator<String> iter = ts.descendingIterator();

        while(iter.hasNext()) {
            String item = iter.next();
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B1_homework b = new B1_homework();
        b.in();
        b.out();
    }
}

class B1_homework2 {
    TreeSet<String> ts = new TreeSet<>();

    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }

    void out() {
        int size = ts.size();
        System.out.println("size : " + size);

        Iterator<String> iter = ts.descendingIterator();

        while(iter.hasNext()) {
            String item = iter.next();
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B1_homework2 b = new B1_homework2();
        b.in();
        b.out();
    }
}

class B1_homework3 {
    TreeSet<String> ts = new TreeSet<>();

    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        Iterator<String> iter = ts.iterator();
        while(iter.hasNext()) {
            String item = iter.next();
            System.out.println("item: " + item);
        }
    }

    public static void main(String args[]) {
        B1_homework3 b3 = new B1_homework3();
        b3.in();
        b3.out();
    }
}

class B1_homework4 {
    TreeSet<String> ts = new TreeSet<>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        Iterator<String> iter = ts.iterator();
        while(iter.hasNext()) {
            String item = iter.next();
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B1_homework4 b4 = new B1_homework4();
        b4.in();
        b4.out();
    }
}

class B1_homework5 {
    TreeSet<String> ts = new TreeSet<>();

    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        Iterator<String> iter = ts.descendingIterator();
        while(iter.hasNext()) {
            String item = iter.next();
            System.out.println("item: " + item);
        }
    }

    public static void main(String args[]) {
        B1_homework5 b5 = new B1_homework5();
        b5.in();
        b5.out();
    }
}