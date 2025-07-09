class B1 {
	B1() {
		System.out.println("2");
		m1();
		System.out.println("4");
		m2();
		System.out.println("6");
	}
	void m1() {
		System.out.println("3");
	}

	void m2() {
		System.out.println("5");
	}

	public static void main(String[] args) {
		System.out.println("1");
		new B1();
		System.out.println("7");
	}
}

class B2 {
	
	B2() {
		System.out.println("B");
		m1();
		System.out.println("D");
		m2();
		System.out.println("F");
	}

	void m2() {
		System.out.println("E");
	}

	void m1() {
		System.out.println("C");
	}

	public static void main(String[] args) {
		System.out.println("A");
		new B2();
		System.out.println("G");
	}
}

class B3 {
	B3() {
		System.out.println("2");
		m1();
		System.out.println("4");
		m3();
		System.out.println("6");
	}

	void m1() {
		System.out.println("3");
	}

	void m3() {
		System.out.println("5");
	}

	public static void main(String[] args) {
		System.out.println("1");
		new B3();
		System.out.println("7");
	}
}

class B4 {
	B4() {
		System.out.println("나");
		h1();
		System.out.println("라");
		h2();
		System.out.println("바");
	}

	void h1() {
		System.out.println("다");
	}

	void h2() {
		System.out.println("마");
	}

	public static void main(String[] args) {
		System.out.println("가");
		new B4();
		System.out.println("사");
	}
}

class B5 {
	B5() {
		System.out.println("야");
		w1();
		System.out.println("여");
		w2();
		System.out.println("요");
	}

	void w1() {
		System.out.println("어");
	}
	void w2() {
		System.out.println("오");
	}

	public static void main(String[] args) {
		System.out.println("아");
		new B5();
		System.out.println("우");
	}
}