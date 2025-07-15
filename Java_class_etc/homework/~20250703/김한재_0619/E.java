class E1 {
	String str = "자바";
	E1() {
	}
	void m() {
		System.out.println("m()");
	}
}

class EUser1 {
	public static void main(String[] args) {
		E1 e = new E1();
		System.out.println(e.str);
		e.m();
	}
}

//

class E2 {
	String py = "파이썬";
	E2() {
	}

	void p() {
		System.out.println("p()");
	}
}

class EUser2 {
	public static void main(String[] args) {
		E2 e = new E2();
		System.out.println(e.py);
		e.p();
	}
}

class E3 {
	String C = "C언어";
	E3() {
	}
	void a() {
		System.out.println("a()");
	}
}

class EUser3 {
	public static void main(String[] args) {
		E3 e = new E3();
		System.out.println(e.C);
		e.a();
	}
}

class E4 {
	String CPP = "C++";
	E4() {
	}

	void m() {
		System.out.println("m()");
	}
}

class EUser4 {
	public static void main(String[] args) {
		E4 e = new E4();
		System.out.println(e.CPP);
		e.m();
	}
}

class E5{
	String Swift = "Swift";
	E5() {
	}

	void S() {
		System.out.println("S()");	
	}
}

class EUser5 {
	public static void main(String[] args) {
		E5 e = new E5();
		System.out.println(e.Swift);
		e.S();
	}
}