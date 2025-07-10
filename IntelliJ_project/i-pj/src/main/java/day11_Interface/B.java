package day11_Interface;

public class B { //Outter
    int i=0;
    void m() {
        System.out.println("m()");
    }

    class BInner1 { //Inner1클래스
        int i1 = 1;
        void m1() {
            System.out.println("m1()");
        }
    }
    static class BInner2 { //Inner2클래스
        int i2 = 2;
        void m2() {
            System.out.println("m2()");
        }
        public static void main(String args[]) {
            B.BInner1 in1 = new B().new BInner1(); //OCJP
            System.out.println("in1.i1: " + in1.i1);
            in1.m1();

            B.BInner2 in2 =  new B.BInner2(); //OCJP
            System.out.println("in2.i2: " + in2.i2);
            in2.m2();
        }
    }

    interface BInner3 {//Inner3인터페이스
        int i3 = 3;
        void m3();
    }
    static interface BInner4 {//Inner4인터페이스
        int i4 = 3;
        void m4();
    }
}

class BInner3Child implements B.BInner3 {
    public void m3() {
        System.out.println("m3()");
    }
}
class BUser {
    public static void main(String args[]) {
        B.BInner3 in3 = new BInner3Child();
        System.out.println("in3.i3: " + in3.i3);
        in3.m3();
    }
}


