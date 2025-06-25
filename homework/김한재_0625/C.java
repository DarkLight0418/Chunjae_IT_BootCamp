class C1 {
	void m1() {
		int sum = 0;
		for(int i=1; i<=100; i++) {
			System.out.println("i: " + i);
			sum += i;
		}
		System.out.println("합 : " + sum);
	}
	void m2() {
		for(int i=1; i<10; i++) {
			System.out.println("2 * " +i+" = " + 2*i);
		}
	}
	void m3() {
		for(int j=2; j<=9; j++){
			for(int i=1; i<10; i++) {
				System.out.println(j+" * " + i + " = " + j*i);
			}
		}
	}

	public static void main(String[] args) {
		C1 c = new C1();
		//c.m1();
		//c.m2();
		c.m3();
	}
}

class C2 {
	void m1() {
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum = sum + i;
		}
		System.out.println("합: " + sum);
	}

	void m2() {
		for(int i=1; i<10; i++) {
			System.out.println("2 * " + i + " = " + 2*i);
		}
	}

	void m3() {
		for(int j=2; j<=9; j++) {
			for(int i=1; i<10; i++) {
				System.out.println(j+"*"+i+"="+j*i);
			}
		}
	}

	public static void main(String[] args) {
		C2 c = new C2();
		//c.m1();
		//c.m2();
		c.m3();
	}
}

class C3{
	void m1() {
		int sum = 0;
		for(int i=1; i<50; i++) {
			sum += i;
		}
		System.out.println("합: " + sum);
	}

	void m2() {
		for(int i=1; i<10; i++) {
			System.out.println("2 곱하기 " + i + "은 " + 2 * i);
		}
	}

	void m3() {
		for(int a=2; a<=9; a++) {
			for(int b=1; b<10; b++) {
				System.out.println(a+"*"+b+"="+a*b);
			}
		}
	}

	public static void main(String[] args) {
		C3 c = new C3();
		//c.m1();
		c.m2();
		//c.m3();
	}
}

class C4 {
	void m1() {
		int sum = 0, i = 1;
		while (i<=100){
			sum+=i;
			i++;
		}
		System.out.println("합: " +sum);
	}

	void m2() {
		for(int i=1; i<10; i++){
			System.out.println("2*"+i+"="+2*i);
		}
	}
	
	void m3() {
		for(int i=2; i<10; i++){
			for(int j=1; j<=9; j++){
				System.out.println(i+" 곱하기 "+j+"은(는) "+ i*j);
			}
		}
	}

	public static void main(String[] args) {
		C4 c = new C4();
		//c.m1();
		//c.m2();
		c.m3();
	}
}

class C5 {
	void m1() {
		int sum = 0;
		for(int i=1; i<101; i++) {
			sum = sum + i;
		}
		System.out.println("총합 : " + sum);
	}

	void m2() {
		for(int i=1; i<10; i++) {
			System.out.println("2 곱하기 " + i + " 은(는) " + 2*i);
		}
	}

	void m3() {
		for(int j=2; j<10; j++){
			for(int i=1; i<=9; i++) {
				System.out.println(j+" * "+i+" = "+ j*i);
			}
		}
	}
	public static void main(String[] args) {
		C5 c = new C5();
		//c.m1();
		//c.m2();
		c.m3();
	}
}