class H1 {
    H1() {
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        //m8();
        //m9();
        m10();
    }

    void m1() {
        double d = 1/2.0; // 0.5 double로 형변환
        System.out.println("d: " + d);

        int i = 5;
        int r = i%2;
        System.out.println("r: " + r);
    }

    void m2() {
        int i = 0;
        int j = (i++ + 10);
        System.out.println("i: " + i + ", j:" + j);
    }

    void m3() {
        int i = 2;
        System.out.println("i: " + i);
    }

    void m4() {
        int i = 3;
        i %= 2;
        System.out.println("i: " + i);
    }

    void m5() {
        int i = 2<<2;
        System.out.println("i: " + i);
    }

    void m6() {
        int i = 1;
        int j = 1;
        boolean b = i<=j;
        System.out.println("b: " + b);
    }

    void m7() {
        int i = 1;
        boolean b = true;
        boolean r = b || i++<2;
        System.out.println("r: " + r + ", i: " + i);
    }

    void m8() {
        boolean b = false;
        int i = 1;
        b |= (i>1);
        System.out.println("b: " + b);
    }

    void m9() {
        int i = 1;
        String r = (i<0) ? "김치" : "깍두기";
        System.out.println("r: " + r);
    }
    void m10() {
        String str = "여행";
        boolean b = str instanceof String;
        System.out.println("b: " + b);
    }

    public static void main(String[] args) {
        new H1();
    }
}

class H2 {
    H2() {
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        //m8();
        //m9();
        m10();
    }

    void m1() {
        double d = 1/2.0; // 0.5
        System.out.println("d: " + d);

        int i = 5;
        int r = i%2;
        System.out.println("r: " + r);
    }

    void m2() {
        int i = 0;
        int j = (i++ + 10);
        System.out.println("i: " + i + ", j: " + j);
    }

    void m3() {
        int i = 2;
        System.out.println("i: " + i);
    }

    void m4() {
        int i = 3;
        i %= 2;
        System.out.println("i: " + i);
    }

    void m5() {
        int i = 2<<2;
        System.out.println("i: " + i);
    }

    void m6() {
        int i = 1;
        int j = 1;
        boolean b = i<=j;
        System.out.println("b: " + b);
    }

    void m7() {
        int i = 1;
        boolean b = true;
        boolean r = b || i++<2;
        System.out.println("r: " + r + ", i: " + i);
    }

    void m8() {
        boolean b = false;
        int i = 1;
        b |= (i>1);
        System.out.println("b: " + b);
    }

    void m9() {
        int i = 3;
        String r = (i>0) ? "김치" : "깍두기";
        System.out.println("r: " + r);
    }

    void m10() {
        String str = "여행";
        boolean b = str instanceof String;
        System.out.println("b: " + b);
    }

    public static void main(String[] args) {
        new H2();
    }
}

class H3 {
    H3() {
        m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        //m8();
        //m9();
        //m10();
    }
    void m1() {
        double d = 1/2.0;
        System.out.println("d: " + d);

        int i = 5;
        int r = i%2;
        System.out.println("r: " + r);
    }

    void m2() {
        int i = 0;
        int j = (i++ + 10);
        System.out.println("i: " + i + ", j: " + j);
    }

    void m3() {
        int i = 2;
        System.out.println("i: " + i);
    }

    void m4() {
        int i = 3;
        i %= 2;
        System.out.println("i: " + i);
    }

    void m5() {
        int i = 2<<2;
        System.out.println("i: " + i);
    }

    void m6() {
        int i = 1;
        int j = 1;
        boolean b = i<=j;
        System.out.println("b: " + b);
    }

    void m7() {
        int i = 1;
        boolean b = true;
        boolean r = b || i++ < 2;
        System.out.println("r: " + r + ", i: " + i);
    }

    void m8() {
        boolean b = true;
        int i = 3;
        b |= (i > 1);
        System.out.println("b: " + b);
    }

    void m9() {
        int i = 1;
        String r = (i>0) ? "김치" : "깍두기";
        System.out.println("r: " + r);
    }

    void m10() {
        String inte = "10";
        boolean b = inte instanceof String;
        System.out.println("b: " + b);
    }

    public static void main(String[] args) {
        new H3();
    }
}