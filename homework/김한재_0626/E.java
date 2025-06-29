class E1 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==2) continue;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        cc:
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==2) continue cc;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }
    
    public static void main(String[] args) {
        E1 e = new E1();
        //e.m1();
        e.m2();
    }
}

class E2 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==2) continue;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        dd:
        for(int j=0; j<3; j++) {
            for(int i=0; i<6; i++) {
                if(i==5) continue dd;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    public static void main(String[] args) {
        E2 e = new E2();
        //e.m1();
        e.m2();
    }
}

class E3 {
    void m1() {
        for(int i=0; i<6; i++) {
            if (i==3) continue;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        aa:
        for(int j=0; j<4; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) continue aa;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖 1");
        }
        System.out.println("for의 밖 2");
    }

    public static void main(String[] args) {
        E3 e = new E3();
        //e.m1();
        e.m2();
    }
}

class E4 {
    void m1() {
        for(int i=0; i<4; i++){
            if(i==3) continue;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        cc:
        for(int j=0; j<3; j++){
            for(int i=0; i<5; i++){
                if(i==2) continue cc;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    public static void main(String[] args) {
        E4 e = new E4();
        e.m1();
        e.m2();
    }
}

class E5 {
    void m1() {
        for(int i=0; i<5; i++){
            if(i==3) continue;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        ad:
        for(int j=0; j<2; j++){
            for(int i=0; i<5; i++){
                if(i==3) continue ad;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    public static void main(String[] args) {
        E5 e = new E5();
        e.m1();
        e.m2();
    }
}