import java.util.*;

class Input3 {
    Scanner s = new Scanner(System.in);

    void input() {
        p("국어: ");
        int i = s.nextInt();
        pln("유효한 입력 점수:" + i);
    }

    void p(String str) {
        System.out.print(str);
    }

    void pln(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {    
        new Input3().input();
    }
}