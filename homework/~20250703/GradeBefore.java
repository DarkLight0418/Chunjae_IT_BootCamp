import java.util.Scanner;

class GradeBefore {
    Scanner sc = new Scanner(System.in);

    double average = 0.0;
    int total = 0;

    void input(String subject, int a) {

        int[] jumsu = new int[subject.length];
        p(subject + " : ");                      // 과목명 출력
        int jum = sc.nextInt();                 // 사용자로부터 점수 입력
        for (int s = 0; s < a; s++) {
            jumsu[s] = jum;                     // 같은 점수를 a번 jumsu 배열에 저장
        }
        for (int b = 0; b < jumsu.length; b++) {
            total = total + jumsu[b];           // jumsu 배열 전체를 total에 더함
        }
        average = total / a;
        System.out.println("평균 : " + average + ", 총점 : " + total);
    }

    public static void main(String[] args) {
        
    }
}