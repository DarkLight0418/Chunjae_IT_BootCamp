
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Input2 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    void input(String subject) {
        p(subject+ ": ");
        try{
            String line = br.readLine();
        
            line = line.trim();  // 공백 제거
            pln("line: " + line);  // 보기에만 그렇지 문자열임
            int i = Integer.parseInt(line);
            pln("i: " + i);
        } catch(IOException e){
            
        } catch(NumberFormatException ne) {
            pln("헉 숫자의 형태가 아니네요...ㅜㅜ");
            input(subject);
        }
    }

    void p(String str) {
        System.out.print(str);
    }

    void pln(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("사용 예시) java Input2 과목1, 과목2, ...");
            return;
        }

        Input2 a = new Input2();
        for(int i=0; i<args.length; i++) {
            a.input(args[i]);
        }
    }
}