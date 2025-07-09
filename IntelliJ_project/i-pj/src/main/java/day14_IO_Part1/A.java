package day14_IO_Part1;

import java.io.*;

//키보드 -> 모니터
//File -> File
public class A {
    InputStream is1, is2;
    OutputStream os1, os2, os3;
    A() {
        //is = System.in; //키보드
        //os = System.out; //모니터
        try {
            is1 = new FileInputStream("src/main/java/day14/A.java");
            os1 = new FileOutputStream("src/main/java/day14/A.txt");

            is2 = new FileInputStream("D:\\JAE\\Java\\img\\IO.jpg");
            os3 = new FileOutputStream("src/main/java/day14/IO.jpg");
            os2 = System.out;
        }catch(FileNotFoundException fe){}
    }
//    void m1(){
//        try {
//            while(true) {
//                int i = is1.read();
//                if(i == 10) break;
//                os1.write(i);
//                os1.flush();
//                os2.write(i);
//                os2.flush();
//            }
//        }catch(IOException ie){
//        }
//    }
//    void m2(){ //꼭 기억!
//        byte bs[] = new byte[8];
//        try {
//            int count = 0;
//            while((count = is1.read(bs)) != -1) {
//                os1.write(bs, 0, count);
//                os1.flush();
//                os2.write(bs, 0, count);
//                os2.flush();
//            }
//        } catch (IOException e) {
//        }finally{
//            try{
//                is1.close();
//                is2.close();
//                os1.close();
//                os2.close();
//                os3.close();
//            }catch(IOException ie){}
//        }
//    }
    void m3() {  // 이미지 생성을 위해서
        byte[] buffer = new byte[1024];
        int count;
        try {
            while ((count = is2.read(buffer)) != -1) {
                os3.write(buffer, 0, count);
                os3.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        A a = new A();
        //a.m1();
        //a.m2();
        a.m3();
    }
}
