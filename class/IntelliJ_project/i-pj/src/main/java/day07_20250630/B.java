package day07_20250630;

import soo.P;

public class B {
    //String strs[] = new String[4];   // (1)선언 + (2)생성
    String strs[] = {"봄", "여름", "가을", "겨울"};  //(1) + (2) + (3)

    void m1() {
        //strs = new String[4];  // (2)배열 객체 생성
        /*
        
        strs[0] = "봄";     // (3)초기화
        strs[1] = "여름";
        strs[2] = "가을"; 
        strs[3] = "겨울";  

        */

        for(int i=0; i<strs.length; i++) {
            P.pln("strs[" + i + "] : " + strs[i]);
        }

        //P.pln("strs[2] : " + strs[2]);
    }
    public static void main(String[] args) {
        B b = new B();
        b.m1();
    }
}

// 배열은 객체다
// 배열은 저장소다 -> 