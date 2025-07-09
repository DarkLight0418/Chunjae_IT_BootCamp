package day12_Thread_Lambda.homework;

class RunChild implements Runnable {
    public void run() {
        while(true) {
            System.out.println("일1");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

class G1 {
    void use1() {
        Runnable r = new RunChild();
        Thread th = new Thread(r);
        th.start();
    }

    void use2() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("일2");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                }
            }
        };
        Thread th = new Thread(r);
        th.start();
    }

    void use3() {
        Runnable r = () -> {
            while (true) {
                System.out.println("일3");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            }
        };
        Thread th = new Thread(r);
        th.start();
    }

    void use4() {
        Runnable r = () -> work();
        Thread th = new Thread(r);
        th.start();
    }

    void use5() {
        new Thread(() -> work()).start();
    }

    void work() {
        while (true) {
            System.out.println("일5");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }


    public static void main(String[] args) {
        G1 g = new G1();
//        g.use1();
//        g.use2();
//        g.use3();
//        g.use4();
        g.use5();
    }
}

class G2 {
    void use1() {
        Runnable r = new RunChild();
        Thread th = new Thread(r);
        th.start();
    }

    void use2() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("일2");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        };
        Thread th = new Thread(r);
        th.start();
    }

    void use3() {
        Runnable r = () -> {
            while (true) {
                System.out.println("일3");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        };
        Thread th = new Thread(r);
        th.start();
    }

    void use4() {
        Runnable r = () ->  work();
        Thread th = new Thread(r);
        th.start();
    }

    void use5() {
        new Thread(() -> work()).start();
    }

    void work() {
        while (true) {
            System.out.println("일5");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String args[]) {
        G2 g = new G2();
//        g.use1();
//        g.use2();
//        g.use3();
        g.use4();
        g.use5();
    }
}
