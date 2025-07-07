package day12;

import soo.P;

class Restaurant {
    void operate() {
        new ServingMan().start();
        new ServingMan().start();
        new Chef().start();
        //Runnable r = new Master();
        //new Thread(r).start();
    }
    void getPaid() {
        while(true) {
            P.pln("계산을 하다");
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie) {}
        }
    }
    public static void main(String args[]) {
        Restaurant r = new Restaurant();
        r.operate();
        r.getPaid();
    }
}
class ServingMan extends Thread {
    public void run() {
        while(true) {
            P.pln("서빙을 하다");
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie) {}
        }
    }
}
class Chef extends Thread  {
    public void run() {
        while(true) {
            P.pln("요리을 하다");
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ie) {}
        }
    }
}
//class Master implements Runnable {
//	public void run() {
//		while(true) {
//			P.pln("계산을 하다");
//			try {
//				Thread.sleep(1000);
//			}catch(InterruptedException ie) {}
//		}
//	}
//}
