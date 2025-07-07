package homework;

class DTh extends Thread {
    public void run() {
        D.m();
    }
}

public class D {
    static int i;
    static int k;
    D() {
        Thread th1 = new DTh();
        th1.start();

        Thread th2 = new DTh();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException ie) {}

        System.out.println("i: " + i);
    }

    synchronized static void m() {
        for(int j=0; j<100; j++) {
            k++;
            i++;
        }
    }

    public static void main(String[] args) {
        new D();
    }
}
