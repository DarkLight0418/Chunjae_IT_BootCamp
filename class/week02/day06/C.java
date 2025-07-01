class C {
	void m1() { 	// 1부터 100까지의 합
		int s = 0;  // !! for문 바디에 넣게 되면, 반복문이 실행될 때마다 s는 0으로 초기화됨 !!
		
		for (int i = 0; i <= 100 ; i++) {
			s += i;
			// System.out.println("i : " + i);
		}
		System.out.println("합계 : " + s);
	}

	void m2() { /* 2단 => 계속 2를 더하네... => 출력만 할 거면 굳이 변수가 여러개 필요할까??
  => 값을 따로 저장해서 구할 필요가 없지! */
		System.out.println("2단");
		for (int i = 1; i < 10; i++) {
			System.out.println("2 * " + i + " = " + i*2);
		}
	}

	void m3() { // 구구단
		for (int j = 2; j < 10; j++ ){
			System.out.println();
			System.out.println(j+"단");
			
			for (int i = 1; i < 10 ; i++) {
				System.out.println(j + " * " + i + " = " + i * j);
			}
		}
	}

	void m4() { // 구구구단
		for (int k = 2; k <= 9; k++ ){
			System.out.println();
			//System.out.println(j+"단");
			for (int j = 1; j <= 9 ; j++){
				for (int i = 1; i <= 9 ; i++) {
					System.out.println(k + "*" + j + "*" + i + "=" + k*j*i);
					//System.out.printf("%d * %d * %d = %d\n", k, j, i, k*j*i);
				}
			}
		}
	}

	void m5() { // m4()를 while문으로
		int k = 2;
		while (k <= 9){
			int j = 1;
			while (j <= 9){
				int i = 1;
				while (i <= 9) {
					System.out.println(k + "*" + j + "*" + i + "=" + k*j*i);
					i++;
				}
				j++;
			}
			k++;
		}
	}

	public static void main(String[] args) {
		C c = new C();
		//c.m1();
		//c.m2();
		//c.m3();
		//c.m4();
		c.m5();
	}
}