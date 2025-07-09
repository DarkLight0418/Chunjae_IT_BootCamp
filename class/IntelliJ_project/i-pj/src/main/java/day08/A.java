package day08;

import java.util.*;
import soo.P;

public class A {
	Vector v = new Vector();
	
	void in() {
		v.add("봄"); // add() 메소드 : 모든 참조형을 집어 넣을 수 있음
		v.add("여름");
		v.add("가을");
		v.add("겨울");
	}
	void out() {
		int size = v.size();
		P.pln("size : " + size);
		v.add("초겨울");  // 추가
		
		int size2 = v.size();
		P.pln("size2 : " + size2);
		
		v.remove(1);  // 제거
		int size3 = v.size();
		P.pln("size3 : " + size3);
		for(int i=0; i<size; i++) {  // 리스트의 특징 : 집어넣은 순서를 보전, 중복 허용(같은 거 여러번 들어갈 수 있음)
			Object obj = v.get(i);
			String item = (String)obj;
			P.pln("item: " + item);
		}
	}
	
	public static void main(String[] args) {
		A a = new A();
		a.in();
		a.out();
	}
}
