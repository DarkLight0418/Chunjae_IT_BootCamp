package day14.t;

import soo.P;
import java.io.*;
import java.util.Hashtable;
import java.util.*;

public class T1 {
    final String PATH = "src/main/java/day14/t/";
    String fname = "" +
            "original.txt";
    File f;
    File info;
    Map<String, String> map = new Hashtable<>();
    T1(){
        f = new File(PATH + fname);
    }
    void init(){
        try {
            info = new File(PATH + "info.txt");
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
    void showInfo(){
        Set<String> keys = map.keySet();
        for(String key : keys){
            P.pln("key: " + key + ", value: " + map.get(key));
        }
    }
    void m1(){
        if(f.exists()){
            P.pln("존재O");
            if(f.isFile()){
                P.pln("파일");
            }else{
                P.pln("디렉토리");
            }
        }else{
            P.pln("존재X");
        }
    }
    Set<String> set = new TreeSet<>();
    void m2(){ //filtered.txt 생성
        FileWriter fw = null;
        FileReader fr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        String value = map.get("filtered");
        try {
            fw = new FileWriter(PATH + value);

            fr = new FileReader(PATH + map.get("original"));
            br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                set.add(line);
            }
            //showSet();

            pw = new PrintWriter(fw, true);
            for(String item : set) {
                pw.println(item);
            }
        }catch(IOException ie){
        }finally{
            try{
                //fw.close();
                fr.close();
            }catch(IOException ie){}
        }
    }
    void showSet(){
        for(String item : set){
            P.pln("Set item: " + item);
        }
    }
    void m3(){ //original.bak변경
        if(f.exists()){
            String value = map.get("original_back");
            File refname = new File(PATH+value);
            f.renameTo(refname);
        }
    }

    public static void main(String args[]) {
        T1 t1 = new T1();
        //t1.m1();
        t1.init();
        t1.m2();
        t1.m3();
    }
}
