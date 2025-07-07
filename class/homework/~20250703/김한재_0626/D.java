class D1 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==3) break;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if (i==3) break;
                System.out.println("i: " + i);
            }//1
            System.out.println("for의 바깥1");
        }//2
        System.out.println("for의 바깥2");
    }

    void m3() {
        aa:
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) break;
                System.out.println("i: " + i);
            }//1
            System.out.println("for의 밖1");
        }//2
        System.out.println("for의 밖2");
    }

    void m4() {
        int i = 3;
        char c = 'A';
        bb:  // lable
        switch(i) {
            case 1: System.out.println("1"); break;
            case 2:
                switch(c) {
                    case 'A':
                        System.out.println("A"); break bb;
                    case 'B':
                        System.out.println("B");
                } //1
                System.out.println("switch밖1");
                break;
            default: System.out.println("default");
        }//2
        System.out.println("switch밖2");
    }

    public static void main(String[] args) {
        D1 d = new D1();
        d.m1();
        //d.m2();
        //d.m3();
        //d.m4();
    }
}

class D2 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==3) break;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) break;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m3() {
        aa:
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) break aa;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m4() {
        int i = 2;
        char c = 'B';
        bb:
        switch(i) {
            case 1: System.out.println("1"); break;
            case 2:
                switch(c) {
                    case 'A':
                        System.out.println("A"); break;
                    case 'B':
                        System.out.println("B");
                }
                System.out.println("switch밖1");
                break;
            default: System.out.println("default");
        }
        System.out.println("switch밖2");
    }

    public static void main(String[] args) {
        D2 d = new D2();
        d.m1();
        //d.m2();
        //d.m3();
        //d.m4();
    }
}

class D3 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==3) break;
            System.out.println("i: " + i);
        }
    }

    void m2() {
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) break;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m3() {
        aa:
        for (int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) break aa;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m4() {
        int i = 2;
        char c = 'A';
        bb:
        switch(i) {
            case 1: System.out.println("1"); break;
            case 2:
                switch(c) {
                    case 'A':
                        System.out.println("A"); break;
                    case 'B':
                        System.out.println("B");
                }
                System.out.println("switch밖1");
                break;
            default: System.out.println("default");
        }
        System.out.println("switch밖2");
    }

    public static void main(String[] args) {
        D3 d = new D3();
        d.m1();
        //d.m2();
        //d.m3();
        //d.m4();
    }    
}

class D4 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==3) break;
            System.out.println("i:" + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++) {
                if(i==3) break;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m3() {
        aa:
        for (int j=0; j<2; j++) {
            for (int i=0; i<5; i++) {
                if(i==3) break aa;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m4() {
        int i = 2;
        char c = 'A';
        bb:
        switch(i) {
            case 1: System.out.println("1"); break;
            case 2:
                switch(c) {
                    case 'A':
                        System.out.println("A"); break;
                    case 'B':
                        System.out.println("B");
                    
                }
                System.out.println("switch 밖1");
                break;
            default: System.out.println("default");
        }
        System.out.println("switch 밖2");
    }

    public static void main(String[] args) {
        D4 d = new D4();
        d.m1();
        //d.m2();
        //d.m3();
        //d.m4();
    }
}

class D5 {
    void m1() {
        for(int i=0; i<5; i++) {
            if(i==3) break;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }

    void m2() {
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++){
                if(i==3) break;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m3() {
        aa:
        for(int j=0; j<2; j++) {
            for(int i=0; i<5; i++){
                if(i==3) break aa;
                System.out.println("i: " + i);
            }
            System.out.println("for의 밖1");
        }
        System.out.println("for의 밖2");
    }

    void m4() {
        int i = 6;
        char c = 'A';
        bb:
        switch(i) {
            case 6: System.out.println("1"); break;
            case 7:
                switch(c) {
                    case 'A':
                        System.out.println("A"); break;
                    case 'B':
                        System.out.println("B");
                }
                System.out.println("switch밖1");
                break;
            default: System.out.println("default");
        }
        System.out.println("switch밖2");
    }

    public static void main(String[] args) {
        D5 d = new D5();
        d.m1();
        //d.m2();
        //d.m3();
        //d.m4();
    }
}