class G1 {
    void m1() {
        for(int i=0; i<5; i++) {
            //if(i==2) break;
            //if(i==2) continue;
            if(i==2) return;
            System.out.println("i: " + i);
        }
        System.out.println("하나");
    }

    int m2(boolean b) {
        if(b) {
            System.out.println("1");
            return 1;
        }else {
            System.out.println("2");
            return -1;
        }
    }

    public static void main(String[] args) {
        G1 g = new G1();
        g.m1();
        System.out.println("둘");
        int result = g.m2(false);
        System.out.println("result: " + result);
    }
}

class G2 {
    void m1() {
        for(int i=0; i<5; i++) {
            //if(i==2) break;
            //if(i==2) continue;
            if(i==2) return;
            System.out.println("i: " + i);
        }
        System.out.println("하나");
    }

    int m2(boolean b) {
        if(b) {
            System.out.println("1");
            return 1;
        }else {
            System.out.println("2");
            return -1;
        }
    }

    public static void main(String[] args) {
        G2 g = new G2();
        g.m1();
        System.out.println("둘");
        int result = g.m2(true);
        System.out.println("result:" + result);
    }
}

class G3 {
    void m1() {
        for(int i=0; i<5; i++) {
            //if(i==2) break;
            //if(i==2) continue;
            if(i==2) return;
            System.out.println("i: " + i);
        }
    }

    int m2(boolean b) {
        if(b) {
            System.out.println("1");
            return 1;
        }else {
            System.out.println("2");
            return -1;
        }
    }

    public static void main(String[] args) {
        G3 g = new G3();
        g.m1();
        System.out.println("둘");
        int result = g.m2(false);
        System.out.println("result: " + result);
    }
}

class G4 {
    void m1() {
        for(int i=0; i<5; i++) {
            //if(i==2) break;
            //if(i==2) continue;
            if(i==2) return;
            System.out.println("i: " + i);
        }
        System.out.println("하나");
    }

    int m2(boolean b) {
        if(b) {
            System.out.println("1");
            return 1;
        }else {
            System.out.println("2");
            return -3;
        }
    }

    public static void main(String[] args) {
        G4 g = new G4();
        //g.m1();
        //System.out.println("둘");
        int result = g.m2(false);
        System.out.println("result: " + result);
    }
}

class G5 {
    void m1() {
        for(int i=0; i<5; i++) {
            //if(i==2) break;
            //if(i==2) continue;
            //if(i==2) return;
            System.out.println("i: " + i);
        }
        System.out.println("하나");
    }

    int m2(boolean b) {
        if(b) {
            System.out.println("3");
            return 5;
        }else {
            System.out.println("4");
            return 6;
        }
    }


    public static void main(String[] args) {
        G5 g = new G5();
        //g.m1();
        System.out.println("둘");
        int result = g.m2(true);
        System.out.println("result: " + result);
    }
}