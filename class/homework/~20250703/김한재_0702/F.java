



class MyException extends Exception {
    String name = "나의 예외";
    void doIt1() {
        System.out.println("doIt1()");
    }
}

class YourException extends Exception { 
    String name = "너의 예외";
    void doIt2() {
        System.out.println("doIt2()");
    }
}

class ExcepUser {
    boolean flag1;
    boolean flag2;
    boolean flag3 = true;

    ExcepUser() throws MyException {
        if(flag1) {
            throw new MyException();
        }
    }

    void m() throws YourException {
        if(flag2) {
            throw new YourException();
        }
    }
    
    void mm() throws Exception {
        if(flag3) {
            throw new Exception();
        }
    }
}

public class F {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("(1)");
            ExcepUser ex = new ExcepUser();
            System.out.println("(2)");
            ex.m();
            System.out.println("(3)");
            //ex.mm();
            System.out.println("(4)");
        } catch (MyException me) {
            me.doIt1();
            System.out.println(me.name);
            System.out.println("(5)");
        } catch (YourException ye) {
            ye.doIt2();
            System.out.println(ye.name);
            System.out.println("(6)");
        }
        System.out.println("(8)");
    }
}

class F2 {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("(1)");
            ExcepUser ex = new ExcepUser();
            System.out.println("(2)");
            ex.m();
            System.out.println("(3)");
            ex.mm();
            System.out.println("(4)");
        } catch (MyException me) {
            me.doIt1();
            System.out.println(me.name);
            System.out.println("(5)");
        } catch (YourException ye) {
            ye.doIt2();
            System.out.println(ye.name);
            System.out.println("(6)");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("(7)");
        }
        System.out.println("(8)");
    }
}

class F3 {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("(1)");
            ExcepUser ex = new ExcepUser();
            System.out.println("(2)");
            ex.m();
            System.out.println("(3)");
            ex.mm();
            System.out.println("(4)");
        } catch (MyException me) {
            me.doIt1();
            System.out.println(me.name);
            System.out.println("(5)");
        } catch (YourException ye) {
            ye.doIt2();
            System.out.println(ye.name);
            System.out.println("(6)");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("(7)");
        }
        System.out.println("(8)");
    }
}

class F4 {
    public static void main(String[] args) {
        try {
            System.out.println("일");
            ExcepUser ex = new ExcepUser();
            System.out.println("이");
            ex.m();
            System.out.println("삼");
            ex.mm();
            System.out.println("사");
        } catch (MyException me) {
            me.doIt1();
            System.out.println(me.name);
            System.out.println("오");
        } catch (YourException ye) {
            ye.doIt2();
            System.out.println(ye.name);
            System.out.println("육");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(e);
            System.out.println("칠");
        }
        System.out.println("팔");
    }
}

class F5 {
    public static void main(String[] args) {
        try {
            System.out.println(".1");
            ExcepUser ex = new ExcepUser();
            System.out.println(".2");
            ex.m();
            System.out.println(".3");
            ex.mm();
            System.out.println(".4");
        } catch (MyException me) {
            me.doIt1();
            System.out.println(me.name);
            System.out.println(".5");
        } catch (YourException ye) {
            ye.doIt2();
            System.out.println(ye.name);
            System.out.println(".6");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(".7");
        }
        System.out.println(".8");
    }
}