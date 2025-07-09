// set 계열

package day13_stream.basic;

import soo.P;

import java.util.TreeSet;
import java.util.stream.Stream;

public class B { //Set계열
    TreeSet<String> ts = new TreeSet<>(); //오름차순
    //TreeSet<String> ts = new TreeSet<>(Comparator.reverseOrder()); //내림차순
    void in(){
        ts.add("봄");ts.add("여름");ts.add("봄");ts.add("가을");ts.add("겨울");
    }
    void out1(){ //오름차순정렬
        //Stream<String> s = ts.stream(); //오름차순
        Stream<String> s = ts.descendingSet().stream(); //내림차순
        s.forEach(item -> {
            P.p(item + " by " );
            P.pln(Thread.currentThread().getName());
        });
    }
    void out2() { //정렬X
        Stream<String> ps = ts.parallelStream();
        ps.forEach(item -> {
            P.p(item + " by " );
            P.pln(Thread.currentThread().getName());
        });
    }
    public static void main(String args[]){
        B b = new B();
        b.in();
        b.out1();
        //b.out2();
    }
}
