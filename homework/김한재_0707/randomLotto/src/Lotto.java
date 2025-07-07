import java.io.*;
import java.util.*;

public class Lotto {
    String fname = "우리반.txt";
    InputStream is;
    FileReader fr;
    BufferedReader br;

    ArrayList<String> list = new ArrayList<>();

    Lotto() {
        try {
            fr = new FileReader("우리반.txt");
            br = new BufferedReader(fr);
        } catch (FileNotFoundException fe) {
            System.out.println("파일을 찾을 수가 없네요...ㅜㅜ");
        }
    }

    void read() {
        String line = "";
        try {
            int i = 0;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() != 0) {
                    list.add(line);
                }
            }
        } catch (IOException ie) {}
    }

    void  showAll() {
        System.out.println("<추첨>");
        for(String name : list) {
            System.out.println(name);
        }
    }

    Random r = new Random();
    void showOne() {
        int i = r.nextInt(list.size());
        System.out.println("#당청자: " + list.get(i));
    }

    public static void main(String[] args) {
        new Lotto();
    }
}
