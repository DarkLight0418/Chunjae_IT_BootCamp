import java.util.*;

class B1 {
    TreeSet<String> ts = new TreeSet<String>();
    void in() {
<<<<<<< HEAD
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울");
    }

    void out() {
        int size = ts.size();
        System.out.println("size: " + size);

        Iterator<String> iter = ts.iterator();
        while(iter.hasNext()) {
            String item = iter.next();
=======
        ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
    }
    void out() {
        int size = ts.size();
        System.out.println("size:" + size);

        for(String item: ts) {
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        B1 b = new B1();
<<<<<<< HEAD
        b.in();
        b.out();
    }
}
=======
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
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
