// 리스트 계열

package day13_stream.basic;

import soo.P;

import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Stream;

class ConsumerImpl implements Consumer<String> {
    public void accept(String item) {
        P.p(item);
    }
}

public class A {  // List 계열
    Vector<String> v = new Vector<>();

    void in() {
        v.add("봄"); v.add("여름"); v.add("가을"); v.add("겨울");
    }

    void out1() {
        Stream<String> s = v.stream();

        // Consumer<String> c1 = new ConsumerImpl();  // 1
        /* Consumer<String> c2 = new Consumer<String>() {  //2
            @Override
            public void accept(String item) {
                P.p(item);
            }
        }; */
        
//        Consumer<String> c3 = (item) -> P.p(item);  // 3 -> item 괄호 제외 가능
        
        
//        Consumer<String> c4 = P::p;  // 4
//
//        s.forEach(c1);
//        s.forEach(c2);  // forEach(Consumer <? super T> action) ;
//        s.forEach(c3);
//        s.forEach( item -> P.p(item)); // 3-2
//        s.forEach(c4);
//        s.forEach(P::p);  // 4-2

    }
//    void out2() {  // 순서 보존 O
//        Stream<String> s = v.stream();
//
//        s.forEach(item -> {
//            P.p(item + " by ");
//            P.pln(Thread.currentThread().getName());  //  현재 스레드의 이름을 가져오니라
//        });  // 함수형 프로그래밍 형태
//    }


    void out3() {  // 순서 보존 X
        Stream<String> ps = v.parallelStream(); // 병렬 처리로 속도가 빨라짐

        ps.forEach(item -> {
            P.p(item + " by ");
            P.pln(Thread.currentThread().getName());  //  현재 스레드의 이름을 가져오니라
        });
    }
    public static void main(String[] args) {
        A a = new A();
        a.in();
//        a.out1();
        a.out3();
    }
}