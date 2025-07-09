package day14_IO_Part1.t;

import soo.P;
import java.io.*;
import java.util.*;

//filtered.txt -> list -> (random -> O X) + 시간체크 -> score 추가
public class T2 {
    final String PATH = "src/main/java/day14/t/";
    Map<String, String> map = new Hashtable<>();
    void init(){
        try {
            File info = new File(PATH + "info.txt");
            FileReader fr = new FileReader(info);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                String item[] = line.split(":");
                map.put(item[0], item[1]);
            }
            //showInfo();
        }catch(FileNotFoundException fe){
        }catch(IOException ie){}
    }

    List<String> list = new ArrayList<>();
    void m1(){
        FileReader fr = null;
        BufferedReader br = null;
        String filtered = map.get("filtered");
        try{
            String line = "";
            fr = new FileReader(PATH+filtered);
            br = new BufferedReader(fr);
            while((line= br.readLine()) != null){
                list.add(line);
            }
            //showList();
        }catch(FileNotFoundException fe){}
        catch(IOException ie){}
    }
    void showList(){
        for(String item: list){
            P.pln(item);
        }
    }

    Random r = new Random();
    QTimer qt;
    void m2(){
        qt = new QTimer(this);
        qt.start();

        while(true) {
            int idx = r.nextInt(list.size());
            P.pln(list.get(idx));
            ox();
        }
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int score;
    void ox(){
        try {
            String line = br.readLine();
            if(line != null) line.trim();
            if(line.equalsIgnoreCase("o")){
                score++;
            }
        }catch(IOException ie){}
    }
    public static void main(String args[]) {
        T2 t2 = new T2();
        t2.init();
        t2.m1();
        t2.m2();
    }
}
class QTimer extends Thread {
    T2 t2;
    QTimer(T2 t2){
        this.t2 = t2;
    }

    public void run() {
        String timeStr = t2.map.get("time");
        String intervalStr = t2.map.get("interval");
        int time = Integer.parseInt(timeStr);
        int interval = Integer.parseInt(intervalStr);
        int rest = time;

        while (rest > 0) {
            try {
                Thread.sleep(1000 * interval);
                rest = rest - interval;
                P.pln("남은시간: " + rest);
            } catch(Exception e){}
        }
        System.exit(-1);
    }
}
