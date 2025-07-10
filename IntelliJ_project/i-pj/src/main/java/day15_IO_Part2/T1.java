package day15_IO_Part2;

import soo.P;

public class T1 {
    String str = "정현영 50";

    String name;
    int rate;
    void cut1(){
        int idx = str.indexOf(" ");
        //P.pln("idx: " + idx);
        name = str.substring(0, idx); //이상 미만
        String rateStr = str.substring(idx); //이상
        rateStr = rateStr.trim();
        rate  = Integer.parseInt(rateStr);

        show();
    }
    void cut2(){
        String items[] = str.split(" ");
        if(items.length == 2){
            name = items[0];
            name = name.trim();
            String rateStr = items[1];
            rateStr = rateStr.trim();
            rate = Integer.parseInt(rateStr);
        }

        show();
    }
    void show(){
        P.pln("name: " + name);
        P.pln("rate: " + rate);
    }
    public static void main(String args[]){
        T1 t1 = new T1();
        //t1.cut1();
        t1.cut2();
    }
}
