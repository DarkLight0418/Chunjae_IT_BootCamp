class C1 {
	void m1() {
		char c = 'A';
		int i = 1;
		if (c == 'A') {
			p("1");
			if (i == 0) {
				p("1-1");
			} else {
				p("1-2");
			}
		} else {
			p("2");
		}
	}

	void m2() {
		char c = 'A';
		int i = 1;

		switch (c) {
			case 'A':
				p("1");
			switch (i) {
				case 0: p("1-1"); break;
				default: p("1-2");
			}
			break;
		default: p("2");
		}
	}
	
	void p(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		C1 c = new C1();
		c.m1();
		c.m2();
	}
}

class C2 {
	
	void m1() {
		char c = 'a';
		int i = 1;
		if (c == 'a') {
			pln("1");
			if (i == 0){
				pln("1-1");
			} else {
				pln("1-2");
			}
		} else {
			pln("2");
		}
	}

	void m2() {
		char c = 'a';
		int i = 1;

		switch (c){
			case 'a':
				pln("1");
			switch (i){
				case 0: pln("1-1"); break;
				default: pln("1-2");
			}
			break;
		default: pln("2");		
		}
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		C2 c = new C2();
		c.m1();
		c.m2();
	}
}

class C3 {
	void m1() {
		char c = 'A';
		int i = 0;
		if (c == 'A'){
			pln("1");
			if (i==0){
				pln("1-1");
			} else {
				pln("1-2");
			}
		} else {
			pln("2");
		}
	}

	void m2() {
		char c = 'A';
		int i = 0;

		switch (c){
			case 'A':
				pln("1");
			switch (i){
				case 0: pln("1-1");
				default: pln("1-2");
			}
			break;
		default: pln("2");
		}
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		C3 c = new C3();
		c.m1();
		c.m2();
	}
}

class C4 {

	void m1() {
		char c = 'b';
		int i = 2;
		if (c == 'a') {
			p("1");
			if (i == 1) {
				p("1-1");
			} else {
				p("1-2");
			}
		} else {
			p("2");
		}
	}

	void m2() {
		char c = 'b';
		int i = 2;
		
		switch (c) {
			case 'a':
				p("1");
			switch (i) {
				case 0: p("1-1");
				default: p("1-2");
			}
			break;
		default: p("2");
		}
	}
	
	void p(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		C4 c = new C4();
		c.m1();
		c.m2();
	}
}

class C5 {
	char c = 'A';
    int i = 1;  // 멤버 변수 선언
	
	void m1() {
		if (c == 'A'){
			printStr("1");
			if (i == 0) {
				printStr("1-1");
			} else {
				printStr("1-2");
			}
		} else {
			printStr("2");
		}
	}
	
	void m2() {
		switch (c) {
			case 'A' :
				printStr("1");
			switch (i) {
				case 0: printStr("1-1");
				default: printStr("1-2");
			}
			break;
			default: printStr("2");
		}
	}

	void printStr(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		C5 c = new C5();
		c.m1();
		c.m2();
	}
}