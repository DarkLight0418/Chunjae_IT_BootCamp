package homework;

class A1 {
    A1() {
        Thread th1 = new AA1();
        th1.start();
        Thread th2 = new AA1();
        th2.start();
    }

    void m1() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
            System.out.println("일1");
        }
    }
    public static void main(String[] args) {
        A1 a = new A1();
        a.m1();
    }
}

class AA1 extends Thread {
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
            System.out.println("일2");
        }
    }
}

class A2 {
    A2() {
        Thread th1 = new AA2();
        th1.start();
        Thread th2 = new AA2();
    }

    void m1() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
            System.out.println("일1");
        }
    }
    public static void main(String[] args) {
        A2 a = new A2();
        a.m1();
    }
}

class AA2 extends Thread {
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
            System.out.println("일2");
        }
    }
}