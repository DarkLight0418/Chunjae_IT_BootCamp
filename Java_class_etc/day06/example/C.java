class C1 {
	void m1(){ //1~100 합 ( 가우스 초등문제 ) 
		int sum = 0;
		for(int i=1; i<=100; i++){
			System.out.println("i: " + i);
			sum = sum + i;
		}
		System.out.println("합: " + sum);
	}
	void m2(){ //2단 
		for(int i=1; i<10; i++){
			System.out.println("2 * "+i+" =  "+ 2*i);
		}
	}
	void m3(){ //구구단 
		for(int j=2; j<=9; j++){
			for(int i=1; i<10; i++){
				System.out.println(j+" * "+i+" =  "+ j*i);
			}
		}
	}
	void m4(){ //구구구단 ( 2 * 1 * 1 = 2, .. 9 * 9 * 9 = 729 ) 
		
	}
	public static void main(String[] args) {
		C1 c = new C1();
		//c.m1();
		//c.m2();
		c.m3();
	}
} 