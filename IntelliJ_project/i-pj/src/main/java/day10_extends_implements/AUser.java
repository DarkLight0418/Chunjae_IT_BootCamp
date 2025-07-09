package day10_extends_implements;

class AUser extends A {
    AUser() {
        super();  // new A();
    }

    void use() {
        System.out.println("i: " + i);
        m();
    }
    public static void main(String[] args) {
        new AUser().use();
    }
}


