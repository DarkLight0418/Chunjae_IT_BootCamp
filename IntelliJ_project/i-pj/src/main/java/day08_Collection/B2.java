package day08_Collection;

import java.util.*;
import soo.P;

public class B2 { //Generic, Enhanced Loop
    TreeSet<String> ts = new TreeSet<String>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }
    void out() {
        int size = ts.size();
        P.pln("size: " + size);

        for(String item: ts) {
            P.pln("item: " + item);
        }
    }
    public static void main(String[] args) {
        B2 b = new B2();
        b.in();
        b.out();
    }
}

