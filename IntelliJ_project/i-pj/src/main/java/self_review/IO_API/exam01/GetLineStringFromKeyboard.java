package self_review.IO_API.exam01;

import java.io.*;

public class GetLineStringFromKeyboard {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        while(true) {
            System.out.print("키보드 입력을 받습니다 (quit, q 입력 시 종료) ");
            String lineStr = br.readLine();  // 라인 단위로 문자열을 읽음
            if(lineStr.equals("q") || lineStr.equals("quit")) break;
            System.out.print("입력된 내용 : " + lineStr);
            System.out.println();
        }

        br.close();  // 입력 스트림도 닫아주기
    }
}
