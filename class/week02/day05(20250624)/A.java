class A {

	void m1() {
		if (true) {  // if (false)  나머지 조건은 다 true가 됨
			System.out.println("T");  
			// JVM ; -> 한 줄 (if문 실행문이 두 개 이상이면, 꼭 바디 {} 만들기)
		} else {
			System.out.println("F");
		}
		System.out.println("END");  // 구분이 되게끔 확실히 해보기
	}

	void m2() {
		int i = 0;
		
		if (i > 0) {  // 부등호가 항상 먼저 나와야 한다.
			System.out.println("i는 0보다 크다");
		} else if (i < 0) {
			System.out.println("i는 0보다 작다");
		} else {  // 적어도 이 한 블럭은 수행됨.
			System.out.println("i는 0이다");
		}
	}
	
	public static void main(String[] args) {
		A a = new A();
		// a.m1();
		a.m2();
	}
}
