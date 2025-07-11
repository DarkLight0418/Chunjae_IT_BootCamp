package day16_learning.Thread;

// Runnable 인터페이스를 구현하는 SingleThreadEx2 클래스 정의
public class SingleThreadEx2 implements Runnable {
    // 정수형 배열 선언
    private int[] temp;

    // 생성자 - 객체가 생성될 때 초기화되고 실행되어야 할 작업들의 일련 모음
    public SingleThreadEx2() {
        // 크기가 10인 정수형 배열 초기화
        temp = new int[10];

        // 배열에 0부터 9까지 값 채우기
        for (int start = 0; start < temp.length; start++) {
            temp[start] = start;
        }
    }

    // Runnable 인터페이스의 run() 메소드 구현 - 스레드가 실행할 작업 정의
    @Override
    public void run() {
        // 향상된 for문으로 배열의 모든 요소 순회
        for (int start : temp) {
            try {
                // 1초(1000밀리초) 동안 현재 스레드 일시 정지
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // 예외 처리 (여기서는 비어있음)
            }

            // 현재 실행 중인 스레드 이름 출력
            System.out.println("스레드 이름 :" + Thread.currentThread().getName());
            // 현재 처리 중인 배열 요소 값 출력
            System.out.println("temp value : " + start);
        }
    }

    // 메인 메소드 - 프로그램의 시작점
    public static void main(String[] args) {
        // Runnable 구현 객체 생성
        SingleThreadEx2 ct = new SingleThreadEx2();
        // Thread 객체 생성 시 Runnable 구현체와 스레드 이름 전달
        Thread t = new Thread(ct, "첫번째");

        // 스레드 시작 - JVM이 run() 메소드를 호출함
        t.start();
    }
}