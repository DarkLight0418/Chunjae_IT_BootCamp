package soo;
/** 
    자바 출력을 위한 클래스
    @author JAE
    @version 1.0
 */

public class P {

    /** 
      P 클래스의 생성자
    */
    
    public P() {
    }

    /** 
        출력 후 줄바꿈 하지 않는 메소드
        @param str 출력할 문자열
     */

    public static void p(String str) {
        System.out.print(str);
    }

    /** 
    출력 후 줄바꿈 하는 메소드 
     
    */

    public static void pln(String str) {
        System.out.println(str);
    }
}

/*
    (1) 컴파일
    > javac -d . P.java
    (2) 클래스 파일 압축  => 알집 등으로 압축하면 안됨.
    > jar -cvf p.jar JAE
    (3) 문서 제작
    > javadoc -d doc P.java  
 */

 /*

    soo 폴더 == p.jar
 */