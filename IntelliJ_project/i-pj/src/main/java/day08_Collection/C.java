package day08_Collection;

import java.util.*;

import soo.P;

public class C {
    Hashtable h = new Hashtable();
    void in() {
//        h.put(new Integer(1), "봄");
//        h.put(new Integer(22), "여름");
//        h.put(new Integer(3), "가을");
//        h.put(new Integer(41), "겨울");
//        h.put(new Integer(5), "가을");
        h.put(1, "봄");
        h.put(2, "여름");
        h.put(3, "가을");
        h.put(4, "겨울");
        h.put(5, "가을");
    }
    void out() {
        Enumeration e = h.keys();
        while(e.hasMoreElements()) {
            Object keyObj = e.nextElement();
            Object valObj = h.get(keyObj);
            Integer key = (Integer)keyObj;
            String val = (String)valObj;
            P.pln("key: " + key + ", value: " + val);
        }
    }
    public static void main(String[] args) {
        C c = new C();
        c.in(); c.out();
    }
}
