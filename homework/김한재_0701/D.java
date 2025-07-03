class D1 {
    String[] items = {"봄", "여름", "가을", "겨울"};

    void out() {
<<<<<<< HEAD
=======
        for(int i=0; i < items.length; i++) {
            System.out.println("items["+ i +"]: " + items[i]);
        }   
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
        for(String item: items) {
            System.out.println("item: " + item);
        }
    }
<<<<<<< HEAD


    public static void main(String[] args) {
        new D1().out();
}
=======
    public static void main(String[] args) {
        new D1().out();
    }        
}

class D2 {
    String[] items = {"봄", "여름", "가을", "겨울"};
    void out() {
        for(int i=0; i<items.length; i++) {
            System.out.println("items["+ i +"]: " + items[i]);
        }

        for(String item: items) {
            System.out.println("item:" + item);
        }
    }
    public static void main(String[] args) {
        new D2().out();
    }
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
}
