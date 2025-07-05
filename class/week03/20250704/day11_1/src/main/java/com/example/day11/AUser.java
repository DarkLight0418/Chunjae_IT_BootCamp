// 인터페이스 임포트 AUser.java
package com.example.day11;

import com.example.day11.A1;
import com.example.day11.A2;
import com.example.day11.AInterface;

class AChild implements AInterface {
	public void m1() {
		System.out.println("m1()");
	}
	public void mm() {
		System.out.println("mm()");
	}
	public void mmm() {
		System.out.println("mmm()");
	}
}
class AUser {
	public static void main(String args[]) {
		AChild child =  new AChild();
		
		AChild a = child; //자동형변환
		System.out.println("a.I: " + a.I);
		a.m1();
		a.mm();
		a.mmm();
		a.m2();
		System.out.println();
		
		A1 a1 = a; //자동형변환
		System.out.println("a1.I: " + a1.I);
		a1.m1();
		a1.m2();
		A1.m3();
		System.out.println();
		
		A2 a2 = a; //자동형변환
		a2.mm();
	}
}

