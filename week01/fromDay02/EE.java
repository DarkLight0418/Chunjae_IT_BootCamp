class EE {
	String str = "자바"; 
	EE() {} // Default Constructor

	void m() {
		System.out.println("m()");
	}
}

class EUser {
	public static void main(String[] args) {
		EE e = new EE();
		System.out.println(e.str);  // 속성 사용
		e.m();
	}
}
