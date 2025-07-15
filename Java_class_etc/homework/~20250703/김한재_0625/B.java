class B1{
	void m1() {
		int i=0;
		while(i<5) {
			System.out.println("i: " + i++);
		}
	}
	void m2() {
		int i=0;
		do{
			System.out.println("i: " + i++);
		}while(i<5);
	}
	void m3(){
		for(int i=0; i<5; i++) {
			System.out.println("i: " + i);
		}
	}

	public static void main(String[] args) {
		B1 b = new B1();
		//b.m1();
		//b.m2();
		b.m3();
	}
}

class B2 {
	void m1() {
		int i=0;
		while(i<5) {
			System.out.println("i: " + i++);
		}
	}
	void m2() {
		int i=0;
		do{
			System.out.println("i: " + i++);
		}while(i<5);
	}
	void m3() {
		for(int i=0; i<5; i++) {
			System.out.println("i: " + i);
		}
	}
	public static void main(String[] args) {
		B2 b = new B2();
		//b.m1();
		b.m2();
		//b.m3();
	}
}

class B3{
	void m1(){
		int i=0;
		while(i<5) {
			System.out.println("i: " + i++);
		}
	}
	void m2(){
		int i=0;
		do{
			System.out.println("i: " + i++);
		}while(i<5);
	}
	void m3(){
		for(int i=0; i<5; i++) {
			System.out.println("i: " + i);
		}
	}

	public static void main(String[] args) {
		B3 b = new B3();
		b.m1();
		//b.m2();
		//b.m3();
	}
}

class B4{
	void m1() {
		int i=0;
		while(i<5) {
			System.out.println("i: " + i++);
		}
	}
	void m2() {
		int i=0;
		do {
			System.out.println("i: " + i++);
		}
		while (i<5);
	}
	void m3() {
		for(int i=0;i<5;i++) {
			System.out.println("i: " + i);
		}
	}

	public static void main(String[] args) {
		B4 b = new B4();
		//b.m1();
		//b.m2();
		b.m3();
	}
}

class B5 {

	void m1() {
		int i=0;
		while(i<5) {
			System.out.println("i: " + i++);
		}
	}

	void m2() {
		int i=0;
		do {
			System.out.println("i: " + i++);
		}
		while (i<5);
	}
	
	void m3() {
		for(int i=0; i<5; i++) {
			System.out.println("i: " + i);
		}
	}

	public static void main(String[] args) {
		B5 b = new B5();
		b.m1();
		System.out.println();
		b.m2();
		System.out.println();
		b.m3();
	}
}