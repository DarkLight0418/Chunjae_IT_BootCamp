package aa.bb;

import java.lang.*;

class D1 {
	String name = "김유신";
	D1() {
	}  
	void m(){
	}
}


// 맨 위에만 써야 하기에 별도로 주석 처리했습니다!
// package aa.bb
// import java.lang.*;

class D2 {
	String name = "이순신";
	D2() {
	}
	void m(){
	}
}

// package aa.bb;

// import java.lang.*;

class D3 {
	String name = "조광조";
	D3() {
	}
	void m() {
	}
}

/*
package aa.bb;

import java.lang.*;  // 묵시적 임포트
*/

class D4 {
	String name = "윤봉길";
	D4() {
	}
	void m() {
	}
}

/*
package cc.dd;

import java.lang.*;

*/
class D5 {
	String name = "안창호";
	D5() {
	}
	void m() {
		System.out.println(name);
	}
}

class Execute {
	public static void main(String[] args) {
		D5 d5 = new D5();
		d5.m();
	}
}