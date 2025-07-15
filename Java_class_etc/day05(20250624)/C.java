class C {
	char c = 'B';
	int i = 1;

	void m1() {
		char c = 'B';
		int i = 1;
		if (c == 'A') {
			System.out.println("1");
			if(i>0) {
				System.out.println("1-1");
			} else {
				System.out.println("1-2");
			}
			System.out.println("3");
		} else {
			System.out.println("2");
		}
	}
	
	void m2() {  // m1() if -> m2() switch  -> 일반적으로 전체를 다 바꾸는 것은 힘들다...
		// 비교 연산자 사용 불가능....
		switch (c) {
			case 'A' : 			
				System.out.println("1");
			switch(i) {
				case 0: System.out.println("1-1"); break;
				default: System.out.println("1-2");
			}
		}
	}

	public static void main(String[] args) {
		C c = new C();
		c.m2();
		// c.m1();
	}
}