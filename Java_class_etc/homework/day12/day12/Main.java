package day12;

class Blue implements Runnable {

    @Override
    public void run() {
        while(true){
            try {
            System.out.println("2");
            Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}


class White implements Runnable {

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("1");
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        White white = new White();
        Blue blue = new Blue();

        Thread t1 = new Thread(white);
        Thread t2 = new Thread(blue);

        t1.start();
        t2.start();

        Thread thread = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("파일 업로드중");
                }
            }
        };

        thread.start();
    }
}