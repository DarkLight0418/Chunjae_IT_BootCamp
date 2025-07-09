package day08_Collection;

//import java.util.*;
//import soo.P;

//public class C1 { //Generic, AutoBoxing
//    Hashtable<Integer, String> h = new Hashtable<Integer, String>();
//    void in() {
//        h.put(1, "봄");
//        h.put(2, "여름");
//        h.put(3, "가을");
//        h.put(4, "겨울");
//        h.put(5, "가을");
//    }
//    void out() {
//        Enumeration<Integer> e = h.keys();
//        while(e.hasMoreElements()) {
//            Integer key = e.nextElement();
//            String val = h.get(key);
//            P.pln("key: " + key + ", value: " + val);
//        }
//    }
//    public static void main(String[] args) {
//        C1 c = new C1();
//        c.in(); c.out();
//    }
//}


import java.util.*;
import soo.P;

public class C1 { //Generic, AutoBoxing
    Hashtable<Integer, String> h = new Hashtable<Integer, String>();
    void in() {
        h.put(1, "봄");
        h.put(2, "여름");
        h.put(3, "가을");
        h.put(4, "겨울");
        h.put(5, "가을");
    }
    void out() {
        Enumeration<Integer> e = h.keys();
        while(e.hasMoreElements()) {
            Integer key = e.nextElement();
            String val = h.get(key);
            P.pln("key: " + key + ", value: " + val);
        }
    }
    public static void main(String[] args) {
        C1 c = new C1();
        c.in(); c.out();
    }
}
