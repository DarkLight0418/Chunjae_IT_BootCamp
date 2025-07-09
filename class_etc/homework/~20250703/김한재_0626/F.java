class F1 {
    F1() {
        boolean b = false;
        System.out.println("1");
        if (b) return; // 1
        System.out.println("2");
        m();
        System.out.println("3");
    }

    void m() {
        boolean b = true;
        System.out.println("4");
        if (b) return;  // 2
        System.out.println("5");
    }

    public static void main(String[] args) {
        System.out.println("0");
        F1 f = new F1();
        boolean b = true;
        if(b) return;
        System.out.println("6");
    }
}

class F2 {
    F2() {
        boolean b = true;
        System.out.println("1");
        if(b) return;
        System.out.println("2");
        m();
        System.out.println("3");
    }
    void m() {
        boolean b = true;
        System.out.println("4");
        if(b) return;
        System.out.println("5");
    }

    public static void main(String[] args) {
        System.out.println("0");
        F2 f = new F2();
        boolean b = true;
        if(b) return;
        System.out.println("6");
    }
}

class F3 {
    F3() {
        boolean b = true;
        System.out.println("1");
        if(b) return;
        System.out.println("2");
        m();
        System.out.println("3");
    }

    void m() {
        boolean b = true;
        System.out.println("4");
        if(b) return;
        System.out.println("5");
    }

    public static void main(String[] args) {
        System.out.println("0");
        F3 f = new F3();
        boolean b = false;
        if(b) return;
        System.out.println("6");
    }
}

class F4 {
    F4() {
        boolean b = false;
        System.out.println("1");
        if(b) return;
        System.out.println("2");
        m();
        System.out.println("3");
    }

    void m() {
        boolean b = true;
        System.out.println("4");
        if (b) return;
        System.out.println("5");
    }

    public static void main(String[] args) {
        System.out.println("0");
        F4 f = new F4();
        boolean b = true;
        if(b) return;
        System.out.println("6");
    }
}

class F5 {
    F5() {
        boolean b = false;
        System.out.println("1");
        if(b) return;
        System.out.println("2");
        m();
        System.out.println("3");
    }

    void m() {
        boolean b = true;
        System.out.println("4");
        if (b) return;
        System.out.println("5");
    }

    public static void main(String[] args) {
        System.out.println("0");
        F5 f = new F5();
        boolean b = true;
        if(b) return;
        System.out.println("6");
    }
}