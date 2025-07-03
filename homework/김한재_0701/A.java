import java.util.*;

class A1 {
    Vector v = new Vector();
    void in() {
        v.add("봄"); v.add("여름"); v.add("가을"); v.add("겨울");
    }

    void out() {
        int size = v.size();
        System.out.println("size: " + size);

        for(int i = 0; i < size; i++) {
            Object obj = v.get(i);
            String item = (String)obj;
            System.out.println("item: " + item);
        }
    }

    public static void main(String[] args) {
        A1 a = new A1();
        a.in();
        a.out();
    }
}

class A2 {
    Vector v = new Vector();
    void in() {
        v.add("아침"); v.add("점심"); v.add("저녁");
    }

    void out() {
        int size = v.size();
        System.out.println("size: " + size);
    }

    public static void main(String[] args) {
        A2 a = new A2();
        a.in();
        a.out();
    }
}

class A3 {
    Vector v = new Vector();

    void in() {
        v.add("봄"); v.add("여름"); v.add("가을"); v.add("겨울");
    }
    
    void out() {
        int size = v.size();
        System.out.println("size: " + size);
    }
    public static void main(String[] args) {
        A3 a = new A3();
        a.in();
        a.out();
    }
}

class A4 {
    Vector<String> v = new Vector<String>();

    void in() {
        v.add("봄"); v.add("여름"); v.add("가을"); v.add("겨울"); v.add("가을");
    }
    void out() {
        int size = v.size();
        System.out.println("size: " + size);
        
        for(int i=0; i<size; i++) {
            String item = v.get(i);
            System.out.println("item :" + item);
        }
    }

    public static void main(String[] args) {
        A4 a = new A4();
        a.in(); a.out();
    }
}

class A5 {
    Vector<String> v = new Vector<String>();

    void in() {
        v.add("봄"); v.add("여름"); v.add("가을"); v.add("겨울"); v.add("가을");
    }
    void out() {
        int size = v.size();
        System.out.println("size: " + size);
        
        for(String item: v) {
            System.out.println("item :" + item);
        }
    }
    public static void main(String[] args) {
        A5 a = new A5();
        a.in(); a.out();
    }
}