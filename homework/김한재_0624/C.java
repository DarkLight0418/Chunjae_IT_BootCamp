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