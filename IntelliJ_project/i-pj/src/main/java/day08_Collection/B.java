// Set과 iterator 예제
// Set(집합)은 중복을 허용하지 않음;

package day08_Collection;

import java.util.*;

import soo.P;

public class B {
	TreeSet ts = new TreeSet();
	void in() {
		ts.add("봄"); ts.add("여름"); ts.add("가을"); ts.add("겨울"); ts.add("가을");
	}
	void out() {
		int size = ts.size();
		P.pln("size: " + size);

		Iterator iter = ts.iterator();
		//Iterator iter = ts.descendingIterator();
		while(iter.hasNext()) {
			Object obj = iter.next();
			String item = (String)obj;
			P.pln("item: " + item);
		}
	}
	public static void main(String[] args) {
		B b = new B();
		b.in();
		b.out();
	}
}
