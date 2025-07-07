class D1 {
	void m1() {
		for(int i=0, j=5; i<j; i++, j--)
			System.out.println("i: " + i + ", j: " + j);
	}
	
	void m2() {
		int jjsum = 0, holsum = 0;
		for(int i=0; i<=100; i++){
			if(i%2==1) {
				holsum += i;
			}else {
				jjsum += i;
			}
		}
		System.out.println(
			"홀수합: "+holsum+", 짝수합: " + jjsum + ", 전체합: " +(holsum+jjsum));
	}

	void m3() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<4; i++){
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		D1 d = new D1();
		d.m1();
		d.m2();
		d.m3();
	}
}

class D2 {
	
	void m1(){
		for(int i=0, j=5; i<j; i++, j--)
			System.out.println("i: "+i+", j: "+j);
	}

	void m2() {
		int sum246 = 0;
		int sum135 = 0;
		for(int i=0; i<=100; i++){
			if(i%2==1){
				sum135 += i;
			}else {
				sum246 += i;
			}
		}
		System.out.println(
			"홀수합: "+sum135+", 짝수합: "+sum246+", 전체합: "+(sum135+sum246));
	}
	
	void m3() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<4; i++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}


	public static void main(String[] args) {
		D2 d = new D2();
		d.m1();
		d.m2();
		d.m3();
	}
}

class D3 {
	void m1() {
		for(int i=0, j=5; i<j; i++, j--)
			System.out.println("i:"+i+", j:"+j);
	}
	
	void m2() {
		int jjsum = 0, holsum = 0;
		for(int i=0; i<=100; i++) {
			if(i%2==1){
				holsum += i;
			} else {
				jjsum += i;
			}
		}
		System.out.printf("홀수합: %d, 짝수합: %d, 전체합: %d\n", holsum, jjsum, (holsum+jjsum));
	}

	void m3() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<4; i++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		D3 d = new D3();
		d.m1();
		d.m2();
		d.m3();
	}
}

class D4 {
	void m1(){
		for(int i=0, j=5; i<j; i++, j--)
			System.out.println("i, j : " + i + "," + j);
	}
	
	void m2(){
		int odd = 0;
		int even = 0;
		for(int i=0; i<=100; i++){
			if(i%2==1){
				odd += i;
			}else {
				even += i;
			}
		}
		System.out.printf("odd: %d, even: %d, sum: %d\n", odd, even, odd+even);
	}

	void m3(){
		for(int j=0; j<4; j++){
			for(int i=0; i<4; i++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		D4 d = new D4();
		d.m1();
		d.m2();
		d.m3();
	}
}

class D5 {
	void m1() {
		for(int i=0, j=5; i<j; i++, j--)
			System.out.println("i: " + i + ", j: " + j);
	}

	void m2() {
		int odd = 0, even = 0;
		for(int i=0; i<=100; i++){
			if(i%2==1){
				odd += i;
			} else {
				even += i;
			}
		}
		System.out.println("홀수합:"+odd+", 짝수합:"+even+", 전체합:"+(odd+even));
	}

	void m3() {
		for(int j=0; j<4; j++){
			for(int i=0; i<4; i++){
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		D5 d = new D5();
		d.m1();
		d.m2();
		d.m3();
	}
}