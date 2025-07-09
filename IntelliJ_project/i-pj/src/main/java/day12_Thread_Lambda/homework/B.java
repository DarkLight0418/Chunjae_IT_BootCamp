package day12_Thread_Lambda.homework;

class Restaurant {
    void operate() {
        new ServingMan().start();
    }

    void getPaid() {
        while(true) {
            System.out.println("계산을 하다");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
        }
    }

    public static void main(String[] args) {
        Restaurant r = new Restaurant();
        r.operate();
        r.getPaid();
    }
}

class ServingMan extends Thread {
    public void run() {
        while(true) {
            System.out.println("서빙을 하다");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
        }
    }
}
class Chef extends Thread {
    public void run() {
        while(true) {
            System.out.println("요리를 하다");
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ie) {}
        }
    }
}