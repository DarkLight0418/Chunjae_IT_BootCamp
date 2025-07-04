class A1 {
	final int i = 1; // 멤버1
	static int j; // 멤버2

	void m1(String str1) {  // 지역1 : 파라미터
		System.out.println("str1: " + str1);
	}

	void m2() {
		String str2 = "깍두기"; // 지역2 : 선언초기화
		System.out.println("str2: " + str2);
	}
}

class AUser {
	public static void main(String[] args) {
		A1 a = new A1();
		System.out.println("a.i: "+ a.i); // 객체 소유
		System.out.println("A.j: "+ A.j); // 클래스 소유
		a.m1("현영");
		a.m2();
	}
}

class A2 {
	final int d = 1;
	static int e;

	void m1(String str1) {
		System.out.println("str1: " + str1);
	}
	void m2() {
		String str2 = "김치";
		System.out.println("str2: " + str2);
	}
}


class AUser2 {
	public static void main(String[] args) {
		A2 a = new A2();
		System.out.println("a.i: " + a.i);
		System.out.println("A.j: " + A.j);
		a.m1("한재");
		a.m2();
	}
}

class A3 {
	final int i = 1;
	static int j;

	void m1(String str1) {
		System.out.println("str1: " + str1);
	}
	void m2() {
		String str2 = "깍두기";
		System.out.println("str2: " + str2);
	}
}

class AUser3 {
	public static void main(String[] args) {
		A3 a = new A3();
		System.out.println("a.i: " + a.i);
		System.out.println("A.j: " + A.j);
		a.m1("상욱");
		a.m2();
	}
}

class A4 { 
	final int i = 3;
	static int j;

	void m1(String str3) {
		System.out.println("str3: " + str3);
	}
	void m2() {
		String str4 = "깍두기";
		System.out.println("str4: " + str4);
	}
}

class AUser4 {
	public static void main(String[] args) {
		A4 a = new A4();
		System.out.println("a.i: " + a.i);
		System.out.println("A.j: " + A.j);
		a.m1("창우");
		a.m2();
	}
}

class A5 {
	final int i = 3;
	static int j;

	void m1(String str1) {
		System.out.println("str1: " + str1);
	}
	void m2() {
		String str2 = "깍두기";
		System.out.println("str2: " + str2);
	}
}

class AUser5 {
	public static void main(String[] args) {
		A5 a = new A5();
		System.out.println("a.i: " + a.i);
		System.out.println("A.j: " + A.j);
		a.m1("진석");
		a.m2();
	}
}