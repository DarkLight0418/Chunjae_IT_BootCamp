package day15_IO_Part2;

import soo.P;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class T2 {
    int rate = 50;
    int idx; //당첨자의 index

    Vector<String> names = new Vector<>();
    void inputList(){
        BufferedReader br;
        FileReader fr;
        try {
            fr = new FileReader("src/main/java/day15/list.txt");
            br = new BufferedReader(fr);

            String line = "";
            int count = 0;
            while((line = br.readLine()) != null){
                line = line.trim();
                if(line.length() != 0){
                    String items[] = line.split(" ");
                    if(items.length == 2){
                        names.add(items[0]);
                        idx = count;
                    }else {
                        names.add(line);
                    }
                    count++;
                }
            }

            //show();
        }catch(FileNotFoundException fe){
        }catch(IOException ie){}
    }
    void show(){
        for(String name: names){
            P.pln(name);
        }
    }
    Random r = new Random();
    void get100(){
        int i = r.nextInt(100); //0~99
        if(i < rate){
            P.pln("당첨자: " + names.get(idx));
        }else {
            names.remove(idx);
            int ii = r.nextInt(names.size()); //0~11
            P.pln("당첨자: " + names.get(ii));
        }
    }
    public static void main(String args[]){
        T2 t2 = new T2();
        t2.inputList();
        t2.get100();
    }
}
