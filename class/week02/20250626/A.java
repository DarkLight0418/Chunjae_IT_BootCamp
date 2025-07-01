class A1 {
	void m1() {
		System.out.println("안녕하세요?");
	}

	public static void main(String[] args) {
		A a = new A();
		new A().m1();
		a.m1();
	}
}