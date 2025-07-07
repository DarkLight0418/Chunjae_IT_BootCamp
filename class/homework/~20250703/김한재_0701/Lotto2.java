import java.io.*;
import java.util.*;

public class Lotto2 {
    String fname = "우리반.txt";
    InputStream is;
    FileReader fr;
    BufferedReader br;

    ArrayList<String> list = new ArrayList<String>(); 

    Lotto2() {
        try {
            fr = new FileReader("우리반.txt");
            br = new BufferedReader(fr);

            read();
        } catch(FileNotFoundException fe) {
            System.out.println(fname + "이라는 이름의 파일을 찾을 수 없음.");
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

            showOne();
        } catch (IOException ie) {}
    }

    void showAll() {
        System.out.println("< 모든 멤버들 >");
        for(String name : list) {
            System.out.println(name);
        }
    }

    Random r = new Random();
    void showOne() {
        int i = r.nextInt(list.size());
        System.out.println("#당첨자: "  + list.get(i));
    }
    public static void main(String[] args) {
        new Lotto2();
    }
}