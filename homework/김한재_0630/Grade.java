import java.util.*;

class Grade {
    Scanner sc = new Scanner(System.in);

    int average = 0;
    int total = 0;

    int[] jumsu = new int[100];

    void input(String subject, int a) {
        p(subject + " : ");
        double avg = 0.0;
        int jum = sc.nextInt();
        for (int s = 0; s < a; s++) {
            jumsu[s] = jum;   
            total += jumsu[s];
        }
        avg = total / a;
        System.out.println("평균 : " + avg + ", 총점 : " + total);
    }

    void p(String str) {
        System.out.print(str);
    }

    void pln(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        Grade g = new Grade();
        if (args.length == 0)
            System.out.println("사용예> java Grade 국어 영어 수학");
        else {    
            Scanner sc2 = new Scanner(System.in);
            System.out.print("과목 수 입력: ");
            int k = sc2.nextInt();
            for(int i = 0; i < k; i++) 
                g.input(args[i], k);
        }
    }
}