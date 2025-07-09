package day14;

import java.io.*;

//File -> 모니터
public class B {
    FileInputStream fis;
    OutputStream os;

    BufferedInputStream bis;
    BufferedOutputStream bos;
    B() {
        try {
            fis = new FileInputStream("src/main/java/day14/B.java"); //파일(노드)
            bis = new BufferedInputStream(fis, 512);  // 메모리 버퍼의 사이즈 (입력)
            
            os = System.out; //모니터(노드)
            bos = new BufferedOutputStream(os, 512); // 메모리 버퍼 사이즈 (출력)
        } catch (FileNotFoundException fe){
            System.out.println("해당 파일을 못 찾음");
        }
    }
    void m(){
        byte bs[] = new byte[8];  // byte : size == 배수 관계인 게 좋음
        try {
            int count = 0;
            while((count = bis.read(bs)) != -1) {
                bos.write(bs, 0, count);
            }
            os.flush();
        } catch (IOException e) {
        }finally{
            try{

                bos.close();  // 항상 필터 스트림부터 먼저 닫아주고
                bis.close();
                // os is close() 순서는 관계가 없음
                
                fis.close();
                os.close();  // 노드 스트림을 그 다음 닫아주는 게 좋음
                
                /*
                 항상 나중에 열린 것을 먼저 닫아주는 게 좋음
                */
            }catch(IOException ie) {}
        }
    }
    public static void main(String args[]) {
        B b = new B();
        b.m();
    }
}

