// 배열

package day13.basic;

import soo.P;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class D { //배열(Array)
    String strs[] = {"봄", "여름", "가을", "겨울"};

    void out1(){
        Stream<String> s = Arrays.stream(strs);
        s.forEach(item -> {
            P.p(item + " by ");
            P.pln(Thread.currentThread().getName());
        });
    }

    List<String> toList(){
        //List<String> list = Arrays.asList(strs); //방법1
        List<String> list = Arrays.asList("봄", "여름", "가을", "겨울"); //방법2
        return list;
    }
    void out2(){
        List<String> list = toList();
        Stream<String> s = list.stream();
        s.forEach(item -> {
            P.p(item + " by ");
            P.pln(Thread.currentThread().getName());
        });
    }
    public static void main(String args[]){
        D d = new D();
        //d.out1();
        d.out2();
    }
}

