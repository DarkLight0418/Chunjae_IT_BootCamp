class Human1 {
    String name = "홍길동";
    Human1() {}
    Human1(String name) {
        this.name = name;
    }
    void move() {
        System.out.println("걷는다");
    }
}

class SuperMan1 extends Human1 {
    int power;
    SuperMan1(String name, int power) {
        super(name);
        this.power = power;
    }
    
    void move() {  // 메소드 오버라이딩 ( 재정의 )
        System.out.println("난다");
    }
}

class PepsiMan1 extends Human1 {
    int speed = 90;
    void move() {
        System.out.println("달린다");
    }
}

class God1 {
    public static void main(String[] args) {
        Human1 m = new SuperMan1("클락", 100);
        System.out.println("m.name: " + m.name);
        // System.out.println("m.power: " + m.power);
        m.move();
        System.out.println("");

        SuperMan1 sm = (SuperMan1)m;
        System.out.println("sm.name: " + sm.name);
        System.out.println("sm.power: " + sm.power);
        sm.move();
        System.out.println("");

        PepsiMan1 pm = new PepsiMan1();
        System.out.println("pm.name: "+ pm.name);
        System.out.println("pm.speed:" + pm.speed);
        pm.move();
    }
}