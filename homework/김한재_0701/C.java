import java.util.*;

class C1 {
    Hashtable<Integer, String> h = new Hashtable<Integer, String>();
<<<<<<< HEAD
=======
    
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
    void in() {
        h.put(1, "봄");
        h.put(2, "여름");
        h.put(3, "가을");
        h.put(4, "겨울");
        h.put(5, "가을");
    }
    void out() {
<<<<<<< HEAD
        Enumeration<Integer> e = h.keys();
        while(e.hasMoreElements()) {
            Integer key = e.nextElement();
            String val = h.get(key);
            System.out.println("key: " + key + ", value: " + val);
        }
    }

    public static void main(String[] args) {
        C1 c = new C1();
        c.in(); c.out();
    }
=======
        Set<Integer> keys = h.keySet();
        for(int key: keys) {
            System.out.println("key: " + key + ", value: " + h.get(key));
        }
    }
    public static void main(String[] args) {
        C1 c = new C1();
        c.in(); c.out();
    }    
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
}
