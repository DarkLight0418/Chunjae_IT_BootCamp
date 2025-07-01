import java.util.*;

class C1 {
    Hashtable<Integer, String> h = new Hashtable<Integer, String>();
    
    void in() {
        h.put(1, "봄");
        h.put(2, "여름");
        h.put(3, "가을");
        h.put(4, "겨울");
        h.put(5, "가을");
    }
    void out() {
        Set<Integer> keys = h.keySet();
        for(int key: keys) {
            System.out.println("key: " + key + ", value: " + h.get(key));
        }
    }
    public static void main(String[] args) {
        C1 c = new C1();
        c.in(); c.out();
    }    
}
