class B1 {
	void m1(){
		boolean b = true;
		if(b) {
			System.out.println("참");
		}else {
			System.out.println("거짓");
		}
	}

	void m2() {
		int i = 2;
		switch(i) {
			case 1: System.out.println("1");
			case 2: System.out.println("2");
			default: System.out.println("1도 2도 아니다.");
		}
		System.out.println("switch out");
	}
	public static void main(String[] args) {
		B1 b = new B1();
		b.m1();
		b.m2();
	}
}

class B2 {
	void m1() {
		boolean b = true;
		if(b) {
			System.out.println("참");
		} else {
			System.out.println("거짓");
		}
	}

	void m2() {
		int i = 2;
		switch(i) {
			case 1: System.out.println("1"); break;
			case 2: System.out.println("2"); break;
			default: System.out.println("1도 2도 아니다");
		}
		System.out.println("switch out");
	}

	public static void main(String[] args) {
		B2 b = new B2();
		b.m1();
		b.m2();
	}
}

class B3 {
	void m1() {
		boolean b = false;
		if(b) {
			System.out.println("참");
		} else{
			System.out.println("거짓");
		}
	}

	void m2() {
		int i = 2;
		switch(i) {
			case 1: System.out.println("1"); break;
			case 2: System.out.println("2"); break;
			default: System.out.println("1도 2도 아니다");
		}
		System.out.println("switch out");
	}
	
	public static void main(String[] args) {
		B3 b = new B3();
		b.m1();
		b.m2();
	}
}
