class C1 {
    void m1() {
        int i = 0;
        while(i<3) {
            System.out.println("i: " + i);
            i++;
        }
    }

    void m2() {
        int i = 0;
        do {
            System.out.println("i: " + i);
            i++;
        } while(i<3);
    }

    void m3() {
        for(int i=0; i<3; i++) {
            System.out.println("i: " + i);
        }
    }

    public static void main(String[] args) {
        C1 c = new C1();
        //c.m1();
        //c.m2();
        c.m3();
    }
}

class C2 {
    void m1() {
        int i = 0;
        while(i<3) {
            System.out.println("i: " + i);
            i++;
        }
    }

    void m2() {
        int i = 0;
        do {
            System.out.println("i: " + i);
            i++;
        }while(i<3);
    }

    void m3() {
        for(int i=0; i<3; i++){
            System.out.println("i: " + i);
        }
    }

    public static void main(String[] args) {
        C2 c = new C2();
        //c.m1();
        //c.m2();
        c.m3();
    }
}

class C3 {
    void m1() {
        int i = 0;
        while(i<3) {
            System.out.println("i: " + i);
            i++;
        }
    }

    void m2() {
        int i = 0;
        do{
            System.out.println("i: " + i);
            i++;
        }while(i<3);
    }

    void m3() {
        for(int i=0; i<3; i++) {
            System.out.println("i: " + i);
        }
    }
    
    public static void main(String[] args) {
        C3 c = new C3();
        //c.m1();
        //c.m2();
        c.m3();
    }
}