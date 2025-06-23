class A1 {
	A1() {
		System.out.println("A1 객체 생성자");
	}
	
	void eat() {
		System.out.println("먹습니다");
	}

	void run() {
		System.out.println("달립니다");
	}
	
	public static void main(String[] args) {
		A1 a = new A1();
		a.eat();
		a.run();
	}
}

class A2 {
	A2() {
		System.out.println("A2 객체 생성자");
	}

	void sleep() {
		System.out.println("잠을 잡니다");
	}

	void walk() {
		System.out.println("걷습니다");
	}

	public static void main(String[] args) {
		A2 a = new A2();
		a.sleep();
		a.walk();
	}
}

class A3 {
	A3() {
		System.out.println("A3 객체 생성자");
	}

	void hook() {
		System.out.println("훅");
	}

	void punch() {
		System.out.println("펀치");
	}

	public static void main(String[] args) {
		A3 a = new A3();
		a.hook();
		a.punch();
	}
}

class A4 {
	A4() {
		System.out.println("A4 객체 생성자");
	}

	void angry() {
		System.out.println("화가 나요");
	}
	void sad() {
		System.out.println("슬퍼요");
	}

	public static void main(String[] args) {
		A4 a = new A4();
		a.angry();
		a.sad();
	}
}

class A5 {
	A5() {
		System.out.println("A5 객체 생성자");
	}

	void start() {
		System.out.println("시작!");
	}

	void finish() {
		System.out.println("종료");
	}

	public static void main(String[] args) {
		A5 a = new A5();
		a.start();
		a.finish();
	}
}