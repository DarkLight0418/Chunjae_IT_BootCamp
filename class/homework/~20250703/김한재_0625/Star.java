class Star {
	void s1() {
		for(int j=4; j>0; j--) {
			for (int i=0; i<5-j; i++){
				System.out.print("* ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	void pln1() {
		for(int j=4; j>0; j--) {
			for (int i=0; i<5-j; i++){
				System.out.println("j : " + j + ", i : " + i);
			}
			System.out.println();
		}
	}

	void s2() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<3-j; i++) {
				System.out.print("  ");
			}
			for(int i=0; i<j+1; i++) {
				System.out.print("* ");	
			}
			System.out.println("  ");
		}
		System.out.println();
	}
	
	void pln2() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<3-j; i++) {
				System.out.println("j : " + j + ", i-1 : " + i);
			}
			for(int i=0; i<j+1; i++) {
				System.out.println("j : " + j + ", i-2 : " + i);
			}
			System.out.println();
		}
	}

	void s3() {
		for(int j=4; j>0; j--) {
			for(int i=0; i<j; i++){
				System.out.print("* ");
			}
			System.out.println(" ");
		}
		System.out.println();
	}

	void pln3() {
		for(int j=4; j>0; j--) {
			for(int i=0; i<j; i++){
				System.out.println("j : " + j + ", i : " + i);
			}
			System.out.println();
		}
	}

	void s4() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<j; i++) {
				System.out.print("  ");
			}
			for(int i=0; i<4-j; i++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
	
	void pln4() {
		for(int j=0; j<4; j++) {
			for(int i=0; i<j; i++) {
				System.out.println("j : " + j + ", i-1 : " + i);
			}
			for(int i=0; i<4-j; i++) {
				System.out.println("j : " + j + ", i-2 : " + i);
			}
			System.out.println();
		}
	}



	public static void main(String[] args) {
		Star star = new Star();
		
		star.s1();
		star.s2();
		star.s3();
		star.s4();

		System.out.println("s1");
		star.s1();
		star.pln1();

		System.out.println("s2");
		star.s2();		
		star.pln2();

		System.out.println("s3");
		star.s3();
		star.pln3();

		System.out.println("s4");
		star.s4();
		star.pln4();
	}
}
