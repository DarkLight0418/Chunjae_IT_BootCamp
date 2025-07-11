package lotto;
import java.io.*;
import soo.P;
import java.util.*;

public class MultiLotto {
    String fname = "우리반.txt";
    InputStream is;
    FileReader fr;
    BufferedReader br;
    ArrayList<String> list = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);

    MultiLotto(){
        try {
            fr = new FileReader("src/main/java/lotto/우리반.txt");
            br = new BufferedReader(fr);

            read();
        }catch(FileNotFoundException fe) {
            P.pln(fname + "이란 파일을 못찾음");
        }
    }
    void read() {
        String line = "";
        try {
            int i = 0;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(line.length() != 0) {
                    list.add(line);
                }
            }

            //showAll();
            showOne();
        }catch(IOException ie) {}
    }
    void showAll() { //모든 멤버들 출력
        P.pln("<모든 멤버들>");
        for(String name : list) {
            P.pln(name);
        }
    }

    void choiceCount() {
        System.out.print("추첨 받을 횟수: ");
        int count = sc.nextInt();
        choice(count);
    }

    void choice(int count) {
        for(int i=0; i < count; i++) {
            System.out.println("#당첨자는 : " + list.get(i));
        }
    }

    Random r = new Random();

    void showOne() { //T2의 쏘스를 참고해서 랜덤한 i를 구한다
        int i = r.nextInt(list.size());
        //P.pln("#당첨자: " + list.get(i));
    }

    public static void main(String[] args) {
        MultiLotto t = new MultiLotto();
        t.showAll();
        t.choiceCount();
    }
}

