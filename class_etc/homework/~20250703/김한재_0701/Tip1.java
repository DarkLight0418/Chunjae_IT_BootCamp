import java.io.*;
import java.util.*;

public class Tip1 {
    String fname = "우리반.txt";
    InputStream is;
    FileReader fr;
    BufferedReader br;
    ArrayList<String> list = new ArrayList<String>();

    Tip1() {
        try {
            fr = new FileReader("src/main/resources/우리반.txt");
            br = new BufferedReader(fr);
            
            read();
        }catch(FileNotFoundException fe) {
            System.out.println(fname + "이란 파일을 못찾음");
        }
    }

    void read() {
        String line = "";
        try {
            //int i = 0;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(line.length() != 0) {
                    list.add(line);
                }
            }
            showAll();
        } catch(IOException ie) {}
    }
    void showAll() {
        System.out.println("<모든 멤버들>");
        for(String name : list) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        Tip1 t = new Tip1();
    }
}

class Tip1_2 {
    String fname = "우리반.txt";
    InputStream is;
    FileReader fr;
    BufferedReader br;
    ArrayList<String> list = new ArrayList<String>();

    Tip1_2() {
        try {
            fr = new FileReader("src/main/resources/우리반.txt");
            br = new BufferedReader(fr);
            
            read();
        }catch(FileNotFoundException fe) {
            System.out.println(fname + "이란 파일을 못찾음");
        }
    }

    void read() {
        String line = "";
        try {
            //int i = 0;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(line.length() != 0) {
                    list.add(line);
                }
            }
            showAll();
        } catch(IOException ie) {}
    }
    void showAll() {
        System.out.println("< 모든 멤버들 >");
        for(String name : list) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        Tip1_2 t = new Tip1_2();
    }
}
