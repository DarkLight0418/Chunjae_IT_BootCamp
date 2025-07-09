package 김한재_0701.m;

import java.io.*;
import soo.P;
import java.util.*;

public class Tip1 {
	String fname = "우리반.txt";
	InputStream is;
	FileReader fr;
	BufferedReader br;
	ArrayList<String> list = new ArrayList<String>();
	Tip1(){
		try {
			fr = new FileReader("src/main/resources/우리반.txt");
			br = new BufferedReader(fr);
			
			read();
		}catch(FileNotFoundException fe) {
			P.pln(fname + "이란 파일을 못찾음");
		}
	}
	void read() {
		String line = "";
		try {
			int i = 0; 
			while((line = br.readLine()) != null) {
				line = line.trim();
				if(line.length() != 0) {
					list.add(line);
				}
			}
			
			//showAll();
			showOne();
		}catch(IOException ie) {}
	}
	void showAll() { //모든 멤버들 출력
		P.pln("<모든 멤버들>");
		for(String name : list) {
			P.pln(name);
		}
	}
	void showOne() { //T2의 쏘스를 참고해서 랜덤한 i를 구한다
		//P.pln("당첨자: " + list.get(i));
	}
	public static void main(String[] args) {
		Tip1 t = new Tip1();
	}
}
