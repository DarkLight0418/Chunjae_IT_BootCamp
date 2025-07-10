package day14_IO_Part1.t;

import soo.P;
import java.io.*;
import java.util.*;

//filtered.txt -> list -> (random -> O X) + 시간체크 -> score 추가
public class T2_1 {
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

    int team;
    void readTeam() {
        P.p("#팀번호: ");
        try {
            String line = br.readLine();
            line = line.trim();
            team = Integer.parseInt(line);
        }catch(IOException ie){}
    }
    void showList(){
        for(String item: list){
            P.pln(item);
        }
    }

    Random r = new Random();
    QTimer2 qt;
    void m2(){
        qt = new QTimer2(this);
        qt.start();

        while(true) {
            int idx = r.nextInt(list.size());
            P.pln(list.get(idx));
            ox(idx);
        }
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int score;
    void ox(int idx){
        try {
            String line = br.readLine();
            if(line != null) line.trim();
            if(line.equalsIgnoreCase("o")){
                list.remove(idx);
                score++;
            }
        }catch(IOException ie){}
    }
    void saveAndExit(){
        //1. list -> filterd.txt 저장

        //2. score디렉토리생성 후 그 하위에 팀단위 점수를 파일(ex: 1.txt )에 저장
        String dirScore = map.get("dirScore"); // score/1/test
        File f = new File(PATH+ dirScore);
        if(!f.exists()){
            f.mkdir();
        }
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(PATH + dirScore + "/" + team + ".txt");
            pw = new PrintWriter(fw, true);
            pw.println(score);
        }catch(IOException ie){}

        System.exit(-1);
    }
    public static void main(String args[]) {
        T2_1 t2 = new T2_1();
        t2.init();
        t2.m1();
        t2.readTeam();
        t2.m2();
    }
}
class QTimer2 extends Thread {
    T2_1 t2;
    QTimer2(T2_1 t2){
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
        t2.saveAndExit();
    }
}
