package day13_stream.advanced;

import soo.P;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class K {
    List<User2> list;
    void in(){
        list = Arrays.asList(
                new User2("강감찬", 32),
                new User2("이순신", 22),
                new User2("최영", 42)
        );
    }

    void out1() {
        list.stream().sorted().forEach(user2 -> P.pln(user2.getName()+"("+user2.getAge()+")"));
    }
    void out2() {
        list.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(user2 -> P.pln(user2.getName()+"("+user2.getAge()+")"));
    }

    public static void main(String[] args) {
        K k = new K();
        k.in();
//        k.out1();
        k.out2();
    }
}
