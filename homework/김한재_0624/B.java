class B1 {	
	int score = 89;

	void m1() {
		if (score >= 90){
			pln("A");
		} else if (score >= 80) {
			pln("B");
		} else if (score >= 70) {
			pln("C");
		} else if (score >= 60) {
			pln("D");
		} else {
			pln("F");
		}
	}

	void m2() {
		score = score / 10;
		switch(score) {
			case 9: pln("A"); break;
			case 8: pln("B"); break;
			case 7: pln("C"); break;
			case 6: pln("D"); break;
			default: pln("F");
		}

		pln("switch 탈출");
	}

	void pln(String str) {
		System.out.println(str);
	}
		
	public static void main(String[] args) {
		B1 b = new B1();
		b.m1();
		b.m2();
	}
}

class B2{
	int score = 93;
	
	void m1() {
		if (score >= 90) {
			pln("A");
		} else if (score >= 80) {
			pln("B");
		} else if (score >= 70) {
			pln("C");
		} else if (score >= 60) {
			pln("D");
		} else {
			pln("F");
		}
	}

	void m2() {
		score = score / 10;
		switch (score) {
			case 9: pln("A"); break;
			case 8: pln("B"); break;
			case 7: pln("C"); break;
			case 6: pln("D"); break;
			default: pln("F");
		}

		pln("switch문 나옴");
	}

	
	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		B2 b = new B2();
		b.m1();
		b.m2();
	}
}

class B3 {
	int score = 100;
	
	void m1() {
		if (score >= 90){
			pln("A");
		} else if (score >= 80){
			pln("B");
		} else if (score >= 70){
			pln("C");
		} else if (score >= 60){
			pln("D");
		} else {
			pln("F");
		}
	}

	void m2() {
		score = score / 10;
		switch (score){
			case 10: pln("100"); break;
			case 9: pln("A"); break;
			case 8: pln("B"); break;
			case 7: pln("C"); break;
			case 6: pln("D"); break;
			default: pln("F"); break;
		}
		pln("Switch문 바깥으로");
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		B3 b = new B3();
		b.m1();
		b.m2();
	}
}

class B4 {
	int score = 99;
	
	void m1() {
		if (score >= 90) {
			pln("A");
		} else if (score >= 80) {
			pln("B");
		} else if (score >= 70) {
			pln("C");
		} else if (score >= 60) {
			pln("D");
		} else {
			pln("E");
		}
	}

	void m2() {
		score = score / 10;
		switch (score){
			case 9: pln("A"); break;
			case 8: pln("B"); break;
			case 7: pln("C"); break;
			case 6: pln("D"); break;
			default: pln("F");
		}
		pln("Switch문 탈출");
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		B4 b = new B4();
		b.m1();
		b.m2();
	}
}

class B5 {
	int score = 90;

	void m1() {
		if (score >= 90) {
			pln("A");
		}
		else if (score >= 80) {
			pln("B");
		} else if (score >= 70) {
			pln("C");
		} else if (score >= 60) {
			pln("D");
		} else {
			pln("F");
		}
	}

	void m2() {
		score = (score / 10) * 10;
		switch (score) {
			case 90: pln("A"); break;
			case 80: pln("B"); break;
			case 70: pln("C"); break;
			case 60: pln("D"); break;
			default: pln("F"); break;
		}
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		B5 b = new B5();
		b.m1();
		b.m2();
	}
}