class B {
	void m1() {
		int i = 0; // i = 1; while (i <= 10)
		while (i < 5) {
			System.out.println("i: " + i++); // 실행과 동시에 증감식 실행
		}
	}

	void m2() { //(무한 반복)
		int i=0; //초기식
		do { 
			System.out.println("i : " + i);
		}while (i<5);  //조건식
	}

	void m3() {
		for (int i=0; i<5; i++) {
			System.out.println("i: " + i);
		}	
	}

	public static void main(String[] args) {
		B b = new B();
		//b.m1();
		//b.m2();
		b.m3();
	}
}