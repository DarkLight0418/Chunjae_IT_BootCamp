class D {
	D() {  // 정의
		System.out.println("2");
		m1();  // 호출
		System.out.println("4");
		m2();
		System.out.println("6");
	}

	void m1() { // 정의
		System.out.println("3");
		m2();

	void m2() {
		System.out.println("5");
	}
	public static void main(String[] args) { // 정의
		System.out.println("1");
		new B();  // 호출
		System.out.println("7");
	}
		