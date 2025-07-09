package day08_Collection;

import java.util.*;
import soo.P;

public class B1 { //Generic
    TreeSet<String> ts = new TreeSet<String>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }
    void out() {
        int size = ts.size();
        P.pln("size: " + size);

        Iterator<String> iter = ts.iterator();
        //Iterator<String> iter = ts.descendingIterator();
        while(iter.hasNext()) {
            String item = iter.next();
            P.pln("item: " + item);
        }
    }
    public static void main(String[] args) {
        B1 b = new B1();
        b.in();
        b.out();
    }
}

