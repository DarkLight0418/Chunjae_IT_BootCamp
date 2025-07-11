package day16_learning.Thread;

// Thread 클래스를 상속받는 SingleThreadEx 클래스 정의
public class SingleThreadEx extends Thread {
    // 정수형 배열 선언
    private int[] temp;

    // 생성자: 스레드 이름을 매개변수로 받음
    public SingleThreadEx(String threadName) {
        // 부모 클래스(Thread)의 생성자 호출하여 스레드 이름 설정
        super(threadName);

        // 크기가 10인 정수형 배열 초기화
        temp = new int[10];

        // 배열에 0부터 9까지 값 채우기
        for (int start = 0; start < temp.length; start++) {
            temp[start] = start;
        }
    }

    // Thread 클래스의 run() 메소드 오버라이딩 - 스레드가 실행할 작업 정의
    public void run() {
        // 향상된 for문으로 배열의 모든 요소 순회
        for(int start : temp) {
            try {
                // 1초(1000밀리초) 동안 현재 스레드 일시 정지
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // 스레드가 중단되었을 때 예외 처리
                ie.printStackTrace();
            }
            // 현재 실행 중인 스레드 이름 출력
            System.out.println("스레드 이름: " + currentThread().getName());
            // 현재 처리 중인 배열 요소 값 출력
            System.out.println("temp value : " + start);
        }
    }

    // 메인 메소드 - 프로그램의 시작점
    public static void main(String[] args) {
        // SingleThreadEx 객체 생성하며 스레드 이름을 "첫번째"로 지정
        SingleThreadEx st = new SingleThreadEx("첫번째");
        // 스레드 시작 - JVM이 run() 메소드를 호출함
        st.start();
    }
}