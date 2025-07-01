import java.util.*;

class B1 {
    TreeSet<String> ts = new TreeSet<String>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }
    void out() {
        int size = ts.size();
        System.out.println("size:" + size);

        for(String item: ts) {
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B1 b = new B1();
        b.in(); b.out();   
    }
}

class B2 {
    TreeSet<String> ts = new TreeSet<String>();
    void in() {
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }
    void out() {
        int size = ts.size();
        System.out.println("size:" + size);

        for(String item: ts) {
            System.out.println("item: " + item);
        }
    }
    public static void main(String[] args) {
        B2 b = new B2();
        b.in(); b.out(); 
    }
}   
