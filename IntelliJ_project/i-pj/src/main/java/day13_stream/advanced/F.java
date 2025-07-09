package day13_stream.advanced;

import day13_stream.User;
import soo.P;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class F {
    List<User> list;
    void in(){
        list = Arrays.asList(
                new User("이순신", 22),
                new User("강감찬", 32),
                new User("최영", 42)
        );
    }
    void showNames1(){
        Stream<User> s = list.stream();
        s.forEach(user -> P.pln(user.getName()));
    }
    void showNames2(){
        list.stream().forEach(user -> P.pln(user.getName()));
    }
    public static void main(String args[]){
        F f = new F();
        f.in();
        f.showNames1();
        //f.showNames2();
    }
}