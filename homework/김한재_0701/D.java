class D1 {
    String[] items = {"봄", "여름", "가을", "겨울"};

    void out() {
        for(int i=0; i < items.length; i++) {
            System.out.println("items["+ i +"]: " + items[i]);
        }   
        for(String item: items) {
            System.out.println("item: " + item);
        }
    }
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
}
