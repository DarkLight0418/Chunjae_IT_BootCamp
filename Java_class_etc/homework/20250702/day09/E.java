package day09;

import java.io.*;

import soo.P;

public class E {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void m1() {
		try {
			P.pln("(1)");
			P.p("나이: ");
			String line = br.readLine();
			P.pln("(2)");
			int age = Integer.parseInt(line);
			P.pln("읽은 나이: " + age);
			P.pln("(3)");
		}catch(IOException ie) {
			P.pln("(4)");
		}catch(NumberFormatException ne) {
			P.pln("(5)");
			P.pln("숫자만 가능");
			m1();
		}
		
		P.pln("(6)");
	}
	public static void main(String[] args) {
		E e = new E();
		e.m1();
	}
}
