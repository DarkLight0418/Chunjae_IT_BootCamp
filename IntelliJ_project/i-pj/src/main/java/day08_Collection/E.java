package day08_Collection;

import java.util.Vector;

import soo.P;

public class E { //Tip
    Vector<String> v1 = new Vector<String>(); //1차원
    Vector<String> v2 = new Vector<String>();
    Vector<Vector<String>> v = new Vector<Vector<String>>(); //2차원

    void in() {
        v1.add("봄");v1.add("여름");v1.add("가을");v1.add("겨울");
        v2.add("아침");v2.add("점심");v2.add("저녁");
        v.add(v1); v.add(v2);
    }
    void out() {
        for(Vector<String> item: v) {
            for(String str: item) {
                P.pln(str);
            }
        }
    }
    public static void main(String[] args) {
        E e = new E();
        e.in(); e.out();
    }
}
