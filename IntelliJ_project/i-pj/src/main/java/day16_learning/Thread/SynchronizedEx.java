package day16_learning.Thread;

class ATM implements Runnable {
    private long depositeMoney = 10000;
    private boolean isMotherTurn = true; // 누구 차례인지 추적

    public void run() {
        String threadName = Thread.currentThread().getName();

        synchronized (this) {
            while (getDepositeMoney() > 0) {
                // mother 스레드는 isMotherTurn이 true일 때만 실행
                // son 스레드는 isMotherTurn이 false일 때만 실행
                if ((threadName.equals("mother") && !isMotherTurn) ||
                        (threadName.equals("son") && isMotherTurn)) {
                    try {
                        wait(); // 자기 차례가 아니면 대기
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                // 출금 처리
                withDraw(1000);

                // 차례 변경
                isMotherTurn = !isMotherTurn;

                // 다른 스레드에게 알림
                notify();

                try {
                    Thread.sleep(1000); // 출력 간격 조절
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 모든 출금이 끝나면 대기 중인 스레드에게 알림
            notifyAll();
        }
    }

    public void withDraw(long howMuch) {
        if (getDepositeMoney() > 0) {
            depositeMoney -= howMuch;
            System.out.print(Thread.currentThread().getName() + ", ");
            System.out.printf("잔액 : %d 원 %n", getDepositeMoney());
        } else {
            System.out.print(Thread.currentThread().getName() + ", ");
            System.out.println("잔액이 부족합니다.");
        }
    }

    public long getDepositeMoney() {
        return depositeMoney;
    }
}

public class SynchronizedEx {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Thread mother = new Thread(atm, "mother");
        Thread son = new Thread(atm, "son");
        mother.start();
        son.start();
    }
}
