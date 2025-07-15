class D {
	void m1() {
		for(int i=0, j=5; j>i; i++, j--) // for(;;) {}
			System.out.println("i : " + i + ", j : " + j);
	}

	void m2(){
		for (int j=0; j<4; j++){
			for(int i=0; i<4; i++){
				System.out.print("* ");
			}
			System.out.println(" ");
		}
	}
	
	void m3() {
		for (int j=0; j<4; j++){
			System.out.println(" ");
			for(int i=0; i<j; i++){
				System.out.print("*");
			}
		}
	}
	
	void m4() {
		int holsum = 0;
		int jjsum = 0;
		for (int i = 0; i<=100; i++){
			if(i%2==1) {
				holsum += i;
				System.out.println(i);
			}else {
				jjsum += i;
			}
		}
		System.out.println("홀수의 합계 : " + holsum + " 짝수의 합계 : " + jjsum + " 전체합: " + (holsum+jjsum));
	}

	public static void main(String[] args) {
		D d = new D();
		/*d.m1();
		d.m2();
		d.m3();
	*/
	d.m4();
	}
}
