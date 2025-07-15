class B {
	B() {
		System.out.println("A()");
	}
	void eat() {
		System.out.println("먹는다");
	}
	void run() {
		System.out.println("달린다");
	}

	public static void main(String args[]) {
		System.out.println("m()");
		B a = new B();

		a.eat();
		a.run();
	}
}