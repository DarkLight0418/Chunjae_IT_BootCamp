package day13.homework;

import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Stream;

class ConsumerImpl implements Consumer<String> {
    public void accept(String item) {
        System.out.println(item);
    }
}

class A1 {
    ArrayList<String> arr = new ArrayList<>();
    
    void in() {
        arr.add("봄"); arr.add("여름"); arr.add("가을"); arr.add("겨울");
    }

    void out1() {
        Stream<String> c = arr.stream();
        Consumer<String> c1 = new ConsumerImpl();
        Consumer<String> c2 = new Consumer<String>() {
            @Override
            public void accept(String item) {
                System.out.println(item);
            }
        };
    }

    void out3() {
        Stream<String> ps = arr.parallelStream();

        ps.forEach(item -> {
            System.out.print(item + " by ");
            System.out.println(Thread.currentThread().getName());
        });
    }
    
    public static void main(String[] args) {
        A1 a = new A1();
        a.in();
        // a.out1();
        a.out3();
    }
}

class A2 {
    Vector<String> v = new Vector<>();

    void in() {
        v.add("봄"); v.add("여름"); v.add("가을"); v.add("겨울");
    }

    void out() {
        Stream<String> ps = v.parallelStream();

        ps.forEach(item -> {
            System.out.print(item + " by ");
            System.out.println(Thread.currentThread().getName());
        });
    }

    public static void main(String[] args) {
        A2 a = new A2();
        a.in();
        a.out();
    }
}
