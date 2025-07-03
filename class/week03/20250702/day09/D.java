package day09;

import soo.P;

class D {
	int sum(int i, int j) {
		return i+j;
	}
}

class DUser{ //사용자클래스
	public static void main(String[] args) {
		D d = new D();
		int r = d.sum(100, 200);
		P.pln("합: " + r);
	}
}
