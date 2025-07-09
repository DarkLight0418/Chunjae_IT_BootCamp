// map 계열


package day13.basic;

import soo.P;

import java.util.Hashtable;
import java.util.Set;
import java.util.stream.Stream;

public class C { //Map계열
    Hashtable<Integer, String> ht = new Hashtable<>();

    void in(){
        ht.put(1, "봄");ht.put(2, "여름");ht.put(3, "가을");ht.put(4, "겨울");ht.put(5, "봄");
    }
    void out1(){ //기존과 같음
        Set<Integer> keys = ht.keySet();
        Stream<Integer> s = keys.stream();
        s.forEach(key -> {
            P.p("key: " + key + ", value: " + ht.get(key) + " by ");
            P.pln(Thread.currentThread().getName());
        });
    }
    void out2(){ //멀티쓰레드
        Set<Integer> keys = ht.keySet();
        Stream<Integer> ps = keys.parallelStream();  // 병행 처리 스트림?
        ps.forEach(key -> {
            P.p("key: " + key + ", value: " + ht.get(key) + " by ");
            P.pln(Thread.currentThread().getName());
        });
    }
    public static void main(String args[]){
        C c = new C();
        c.in();
        //c.out1();
        c.out2();
    }
}
