package day13.advanced;

import day13.User;
import soo.P;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class J { //정렬1
    List<User> list;
    void in(){
        list = Arrays.asList(
                new User("강감찬", 32),
                new User("이순신", 22),
                new User("최영", 42)
        );
    }
    void out1(){ //(나이)오름차순
        Stream<User> s = list.stream().sorted((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()));
        s.forEach(user -> P.pln(user.getName()+"("+user.getAge()+")"));
    }
    void out2(){ //(나이)내림차순
        Stream<User> s = list.stream().sorted((u1, u2) -> Integer.compare(u2.getAge(), u1.getAge()));
        s.forEach(user -> P.pln(user.getName()+"("+user.getAge()+")"));
    }
    public static void main(String args[]){
        J j = new J();
        j.in();
        j.out1();
        P.pln("");
        j.out2();
    }
}
