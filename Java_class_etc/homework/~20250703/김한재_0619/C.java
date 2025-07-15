class C1 {
	C1(){
		pln("C1 객체 생성 시작");
		pln("1");
		pln("2");
		pln("3");
		pln("C1 객체 생성 끝");
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		new C1();
	}
}

class C2 {
	C2() {
		pln("C2 객체 생성 시작");
		pln("3");
		pln("2");
		pln("1");
		pln("C2 객체 생성 종료");
	}

	void pln(String c2) {
		System.out.println(c2);
	}

	public static void main(String[] args) {
		new C2();
	}
}

class C3 {
	C3() {
		yaho("C3 생성");
		yaho("하나");
		yaho("둘");
		yaho("셋");
		yaho("야~호~");
	}

	void yaho(String echo) {
		System.out.println(echo);
	}

	public static void main(String[] args) {
		new C3();
	}
}

class C4 {
	C4() {
		football("축구 선수 이름 대기");
		football("메시");
		football("호날두");
		football("손흥민");
		football("이강인");
		football("박지성");
	}

	void football(String name) {
		System.out.println(name);
	}

	public static void main(String[] args) {
		new C4();
	}
}

class C5 { 
	C5() {
		fruit("과일 이름");
		fruit("==========");
		fruit("사과");
		fruit("바나나");
		fruit("포도");
		fruit("딸기");
	}

	void fruit(String name) {
		System.out.println(name);
	}

	public static void main(String[] args) {
		new C5();
	}
}