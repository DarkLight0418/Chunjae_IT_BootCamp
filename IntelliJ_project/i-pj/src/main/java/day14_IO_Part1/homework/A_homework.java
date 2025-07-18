package day14_IO_Part1.homework;

import java.io.*;

public class A_homework {
    InputStream is1;
    OutputStream os1, os2;

    A_homework() {
        try {
            is1 = new FileInputStream("src/main/java/day14_IO_Part1/homework/A_homework.java");
            os1 = new FileOutputStream("src/main/java/day14_IO_Part1/homework/A_2.txt");

            os2 = System.out;
        } catch(FileNotFoundException fe) {}
    }

    void m2() {
        byte[] bs = new byte[8];
        try {
            int count = 0;
            while((count = is1.read(bs)) != -1) {
                os1.write(bs, 0, count);
                os1.flush();
                os2.write(bs, 0, count);
                os2.flush();
            }
        } catch (IOException e) {
        } finally {
            try {
                is1.close();
                os1.close();
                os2.close();
            } catch (IOException ie) {
            }
        }
    }
    public static void main(String[] args) {
        A_homework a1 = new A_homework();
        a1.m2();
    }
}
