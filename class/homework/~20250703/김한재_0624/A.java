class A1{
	void m1() {
		if (true) {
			System.out.println("T1");
			System.out.println("T2");
		} else {
			System.out.println("F1");
			System.out.println("F2");
		}
		System.out.println("END");
	}

	void m2() {
		int i = 0;
		if (i > 0) {
			System.out.println("i는 0보다 크다");
		} else if (i < 0){
			System.out.println("i는 0보다 작다");
		} else {
			System.out.println("i는 0이다.");
		}
	}

	public static void main(String[] args) {
		A1 a = new A1();
		a.m1();
		a.m2();
	}
}

class A2 {
	void m1() {
		if (false) {
			System.out.println("T1");
			System.out.println("T2");
		} else {
			System.out.println("F1");
			System.out.println("F2");
		}
		System.out.println("END");
	}

	void m2() {
		int i = 1;

		if (i > 0) {
			System.out.println("i는 0보다 크다");
		} else if (i < 0) {
			System.out.println("i는 0보다 작다");
		} else {
			System.out.println("i는 0이다.");
		}
	}

	public static void main(String[] args) {
		A2 a = new A2();
		a.m1();
		a.m2();
	}
}

class A3 {
	void m1() {
		if (true) {
			System.out.println("T1");
			System.out.println("T2");
		} else {
			System.out.println("F1");
			System.out.println("F2");
		}
		System.out.println("END");
	}

	void m2(int i) {
		if (i > 0) {
			System.out.println("i는 0보다 크다");
		} else if(i < 0) {
			System.out.println("i는 0보다 작다");
		} else {
			System.out.println("i는 0이다");
		}
	}

	public static void main(String[] args) {
		A3 a = new A3();
		a.m1();
		a.m2(3);
	}
}

class A4 {
	void m1(boolean b) {
		if (b) {
			System.out.println("참1");
			System.out.println("참2");
		} else {
			System.out.println("거짓1");
			System.out.println("거짓2");
		}
	}

	void m2(int j) {
		if (j > 5){
			System.out.println("5보다 크거나 같은 수 입니다.");
		}
		else if (j < 5) {
			System.out.println("5보다 작은 수입니다.");
		}
		else {
			System.out.println("5입니다.");
		}
	}


	public static void main(String[] args) {
		A4 a = new A4();
		a.m1(false);
		a.m2(5);
	}
}

class A5 {
	int k;
	
	void f1(boolean b) { // 주석 부분 다 다시 보기

		if (b) {
			System.out.println("5를 받았습니다.");
			k = 5;
			//return k;
		}
		else {
			System.out.println("6을 받았습니다.");
			k = 6;
			//return k;
		}
		// System.out.println("리턴값은 " + k); // 여기 이 부분 다시 보자
		// return k;
	}

	void f2() {
		int i = 0;
		if (i > 0) {
			System.out.println("i는 0보다 크다");
		} else if (i < 0) {
			System.out.println("i는 0보다 작다");
		} else {
			System.out.println("i는 0이다");
		}
	}
	public static void main(String[] args) {
		A5 a = new A5();
		//a.f1(true); // (정신 맑을 때 다시 한번 살펴보기 ㅜㅜ)
		a.f2();
	}
}