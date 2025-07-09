package day14;

import java.io.*;

public class C {
    Reader r;
    Writer w;
    C() {
        try {
            r = new FileReader("src\\main\\java\\day14\\C.java");  // \\ == /
            w = new FileWriter("src/main/java/day14/C.txt");
        } catch (FileNotFoundException fe) {  // 자식
        } catch (IOException ie) {}  // 부모   > 그러므로 순서가 중요함
    }

    void m() {
        try {
            char cs[] = new char[8];
            int i = 0;
            while ((i = r.read(cs)) != -1) {
                w.write(cs, 0, i);
            }
            w.flush();
        }catch(IOException ie){
        }finally{
            try{
                r.close();
                w.close();
            }catch(IOException ie){}
        }
    }
    public static void main(String args[]) {
        C c = new C();
        c.m();
    }
}
