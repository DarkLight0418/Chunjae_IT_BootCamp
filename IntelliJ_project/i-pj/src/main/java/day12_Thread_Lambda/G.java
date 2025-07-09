
import soo.P;

class RunChild implements Runnable {
    public void run() {
        while(true) {
            P.pln("일1");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

class G {
    void use1() {
        Runnable r = new RunChild();
        Thread th =new Thread(r);   // r을 재료로 해서 thread 만들기
        th.start();
    }

    void use2() {
        Runnable r = new Runnable() {
            public void run() {
                while (true) {
                    P.pln("일2");
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
                P.pln("일3");
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
        Runnable r = () -> work();
        Thread th = new Thread(r);
        th.start();
    }

    void use5() {
        new Thread(() -> work()).start();
    }

    void work() {
        while (true) {
            P.pln("일5");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String args[]) {
        G g = new G();
//        g.use1();
//        g.use2();
        g.use3();
//        g.use4();
        g.use5();
    }
}
