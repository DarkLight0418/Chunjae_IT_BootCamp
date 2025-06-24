class D {
	void m1() {
		int i = 20;
		char c = 'B';
		switch (i) {
			// pln("0"); switch문이 나오면 바로 바디 시작 부분에 case가 나와야 함.  
			case 10:
				pln("1"); break;
			case 20:
				pln("2");
				switch (c) { 
					case 'A': pln("2-1"); break;
					case 'B': pln("2-2"); break;  // break -> 자기를 둘러싼 첫번째 블록을 나감
					case 'C': pln("2-3"); break;
					default: pln("2-4");
					}
				pln("안쪽 switch밖"); break;
			case 30:
				pln("3"); break;
			default:
				pln("4");  // default에는 break 필요 X
		}
		pln("바깥쪽 switch밖");
	}
	void m2() { // m1() -> m2() if;
		int i = 20;
		char c = 'B';
		if (i == 10) {
			pln("1");
		}else if (i == 20) {
			pln("2");
			if (c == 'A') { 
				pln("2-1");
			}else if (c == 'B') {
				pln("2-2");
			}else if (c == 'C') {
				pln("2-3");
			}else pln("2-4");
			pln("안쪽 switch밖");
		}else if (i == 30) {
			pln("3");
		}else {
			pln("4");
		}
		pln("바깥쪽 switch밖");
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		D d = new D();
		d.m1();
	}
}