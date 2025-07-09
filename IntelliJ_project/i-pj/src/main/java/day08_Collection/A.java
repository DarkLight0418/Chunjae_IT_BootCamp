package day08_Collection;

import java.util.*;
import soo.P;

public class A {
	Vector v = new Vector();
	void in() {
		v.add("봄");v.add("여름");v.add("가을");v.add("겨울"); v.add("가을");
	}
	void out() {
		int size = v.size();
		P.pln("size: " + size);

		for(int i=0; i<size; i++) {
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


