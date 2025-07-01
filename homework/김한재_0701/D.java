class D1 {
    String[] items = {"봄", "여름", "가을", "겨울"};

    void out() {
        for(String item: items) {
            System.out.println("item: " + item);
        }
    }


    public static void main(String[] args) {
        new D1().out();
}
}
