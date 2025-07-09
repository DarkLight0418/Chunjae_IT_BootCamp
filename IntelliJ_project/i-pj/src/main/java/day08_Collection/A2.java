package day08_Collection;

import java.util.*;
import soo.P;

public class A2 { //Generic, Enhanced Loop
    Vector<String> v = new Vector<String>();
    void in() {
        v.add("봄");v.add("여름");v.add("가을");v.add("겨울"); v.add("가을");
    }
    void out() {
        int size = v.size();
        P.pln("size: " + size);

        for(String item: v) {
            P.pln("item: " + item);
        }
    }
    public static void main(String[] args) {
        A2 a = new A2();
        a.in();
        a.out();
    }
}

