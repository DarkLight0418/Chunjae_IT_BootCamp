package day13.advanced;

import soo.P;

import day13.User;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class E {
    List<User> list;
    void in(){
        list = Arrays.asList(
                new User("이순신", 22),
                new User("강감찬", 32),
                new User("최영", 42)
        );
    }
    void showAges1(){
        Stream<User> s = list.stream();
        IntStream is = s.mapToInt(user -> user.getAge());
        //is.forEach(age -> P.pln("age: " + age)); //출력방법1
        is.forEach(P::pln); //출력방법2
    }
    void showAges2(){
        list.stream()
                .mapToInt(user -> user.getAge())
                .forEach(age -> P.pln("age: " + age));
    }
    void out1(){
        Stream<User> s = list.stream();
        IntStream is = s.mapToInt(user -> user.getAge());
        OptionalDouble oDouble = is.average();
        double d = oDouble.getAsDouble();
        P.pln("평균(1) d: " + d);
    }
    void out2() {
        double d = list.stream()
                .mapToInt(user -> user.getAge())
                .average()
                .getAsDouble();
        P.pln("평균(2) d: " + d);
    }
    public static void main(String args[]){
        E e = new E();
        e.in();
        //e.showAges1();
        //e.showAges2();
        //e.out1();
        e.out2();
    }
}
