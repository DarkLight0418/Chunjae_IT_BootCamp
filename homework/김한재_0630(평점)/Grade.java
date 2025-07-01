import java.util.*;

class Grade {
    Scanner sc = new Scanner(System.in);
    // 입력 받기 위한 스캐너 라이브러리

    double average = 0.0;
    int total = 0;

    void input(String[] subject, int a) {
        
        int[] jumsu = new int[subject.length];
        
        // subject 문자열 배열의 길이만큼 jumsu[] 정수형 배열 생성

        for (int s = 0; s < a; s++) {
            System.out.print(subject[s] + "점수 입력: ");
            jumsu[s] = sc.nextInt();
            total += jumsu[s]; // 총점
        }
        
        average = total / a;  // 평균
        String gradeA = setGrade(average);
        System.out.println("평균 : " + average + ", 총점 : " + total + ", 학점 : " + gradeA);
    }

    String setGrade(double su) {
        if (su >= 95.0) {
            return "A+";
        } else if(su >= 90.0) {
            return "A";
        } else if(su >= 80.0) {
            return "B";
        } else if(su >= 70.0) {
            return "C";
        } else {
            return "C 미만";
        }
    }

    public static void main(String[] args) {
        Grade g = new Grade();
        if (args.length == 0)  
            System.out.println("(재실행 부탁합니다)사용예> java Grade 국어 영어 수학");
        else {    
            // k에 과목 수 입력 받도록 해줌

            Scanner sc2 = new Scanner(System.in);
            System.out.print("과목 수 입력: ");
            int k = sc2.nextInt();
            if (k != args.length) {
                System.out.println("과목 수가 정확하지 않아요... 다시 실행해주세요");
            } else {
                g.input(args, k);
                
            }
        }
    }
}