package day08;

import java.util.Enumeration;
import java.util.Hashtable;

public class C {
    Hashtable h = new Hashtable();
    
    void in() {
        h.put(1, "봄");
        h.put(2, "여름");
        h.put(3, "가을");
        h.put(4, "겨울");
    }
    void out() {
        Enumeration e = h.keys();
        while(e.hasMoreElements()) {
            Object keyObj = e.nextElement();
            //Object valObj = h.get();
        }
    }

    public static void main(String[] args) {
        
    }
}
