package day16_learning.Thread;

// Main 클래스 - 프로그램의 진입점
class Main {
    public static void main(String[] args) {
        // ThreadEx 객체 두 개 생성 (각각 독립적인 인스턴스)
        ThreadEx threadex = new ThreadEx();
        ThreadEx threadex2 = new ThreadEx();

        // 두 개의 스레드 객체 생성 - 각각 다른 Runnable 구현체와 이름("A", "B") 전달
        Thread thread1 = new Thread(threadex, "A");
        Thread thread2 = new Thread(threadex2, "B");

        // 두 스레드 모두 시작 - 병렬 실행됨
        thread1.start();
        thread2.start();

        // 현재 스레드(main)의 이름을 가져와 출력
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);
    }
}

// Runnable 인터페이스를 구현하는 ThreadEx 클래스
public class ThreadEx implements Runnable {
    // 인스턴스 변수 선언 - 각 ThreadEx 객체마다 독립적인 값을 가짐
    int testNum = 0;

    // Runnable 인터페이스의 run() 메소드 구현 - 스레드가 실행할 작업 정의
    @Override
    public void run() {
        // 10번 반복 실행
        for(int i=0; i<10; i++) {
            // 현재 실행 중인 스레드 이름이 "A"인 경우에만 구분선 출력 및 testNum 증가
            if(Thread.currentThread().getName().equals("A")) {
                System.out.println("=====================");
                testNum++;
            }
            // 현재 스레드 이름과 testNum 값 출력
            System.out.println("ThreadName : "+ Thread.currentThread().getName() + " testNum = " + testNum);

            try {
                // 1초(1000밀리초) 동안 현재 스레드 일시 정지
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // 스레드가 중단되었을 때 예외 처리 - 스택 트레이스 출력
                ie.printStackTrace();
                //logger.error("예외 발생", ie);
            }
        }
    }

}