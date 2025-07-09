package day14;

import java.io.*;

// 키보드 -> 모니터 + 파일
public class D {
    InputStream is = System.in;  // 노드 -> 근원지인 키보드랑 연결되어 있어서
    Reader r = new InputStreamReader(is); // 브릿지 스트림(1바이트에서 2바이트로)
    BufferedReader br = new BufferedReader(r);

    PrintStream ps = System.out;  // 노드

    FileWriter fw;
    PrintWriter pw;

    D(){
        try {
            fw = new FileWriter("src/main/java/day14/D.txt", true);
            pw = new PrintWriter(fw, true);
        } catch (IOException e) {
        }
    }
    void m(){
        try {
            String line = "";
            while((line= br.readLine()) != null) {
                ps.println(line);//모니터에
                //fw.write(line + "\n"); //파일
                //fw.flush();
                pw.println(line); //파일에
            }
        } catch (IOException e) {
        } finally{
            try{
                br.close();
                pw.close();
                is.close();
                fw.close();
            }catch(IOException ie){}
        }
    }
    public static void main(String args[]) {
        D d = new D();
        d.m();
    }
}