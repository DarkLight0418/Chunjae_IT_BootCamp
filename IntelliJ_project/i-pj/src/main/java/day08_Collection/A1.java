package day08_Collection;

import java.util.*;
import soo.P;

public class A1 { //Generic
    Vector<String> v = new Vector<String>();
    void in() {
        v.add("봄");v.add("여름");v.add("가을");v.add("겨울"); v.add("가을");
    }
    void out() {
        int size = v.size();
        P.pln("size: " + size);

        for(int i=0; i<size; i++) {
            String item = v.get(i);
            P.pln("item: " + item);
        }
    }
    public static void main(String[] args) {
        A1 a = new A1();
        a.in();
        a.out();
    }
}
