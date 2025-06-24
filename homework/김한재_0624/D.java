class D1 {
	int i = 20;
	char c = 'B';  // 멤버 변수로 선언
	
	void m1() {
		switch (i) {
			case 10:
				p("1"); break;
			case 20:
				p("2");
			switch (c) {
				case 'A': p("2-1"); break;
				case 'B': p("2-2"); break;
				case 'C': p("2-3"); break;
				default: p("2-4");
			}
			p("안쪽 switch 밖");
			break;
		case 30:
			p("3");
			break;
		default:
			p("4");
		}
		p("바깥쪽 switch 밖");
	}
	
	void m2() {
		if(i == 10) {
			p("1");
		} else if (i == 20){
			p("2");
			if(c == 'A') {
				p("2-1");
			} else if (c == 'B'){
				p("2-2");
			} else if (c == 'C'){
				p("2-3");
			} else {
				p("2-4");
			}
			p("바깥쪽 switch 밖");
		}
	}

	void p(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		D1 d = new D1();
		d.m1();  // switch
		// d.m2();  // if
	}
}

class D2 {
	void m1() {
		int i = 10;
		char c = 'A';
		switch (i){
			case 10:
				pln("1");
				switch (c) {
					case 'A': pln("2-1"); break;
					case 'B': pln("2-2"); break;
					case 'C': pln("2-3"); break;
					default: pln("2-4");
				}
				pln("안쪽 switch 밖");
				break;
			case 30:
				pln("3");
				break;
			default:
				pln("4");
		}
		pln("바깥쪽 switch 밖");
	}
	void m2() {
		int i = 20;
		char c = 'C';
		if (i == 10) {
			pln("1");
		} else if (i == 20) {
			pln("2");
			if (c == 'A') {
				pln("2-1");
			} else if (c == 'B') {
				pln("2-2");
			} else if (c == 'C') {
				pln("2-3");
			} else {
				pln("2-4");
			}
			pln("안쪽 switch 밖");
		} else if (i == 30){
			pln("3");
		} else {
			pln("4");
		}
		pln("바깥쪽 switch 밖");
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		D2 d = new D2();
		d.m1();
		// d.m2();
	}
}

class D3 {
	void m1() {
		int i = 20;
		char c = 'A';
		switch(i) {
			case 10:
				pln("21"); break;
			case 20:
				pln("22");
				switch (c){
					case 'A': pln("22-1"); break;
					case 'B': pln("22-2"); break;
					case 'C': pln("22-3"); break;
					default: pln("22-4");
				}
				pln("안쪽 switch 밖");
				break;
			case 30:
				pln("23");
				break;
			default:
				pln("24");
			}
			pln("바깥쪽 switch 밖");
		}

	void m2() {
		int i = 30;
		char c = 'A';
		if (i == 10) {
			pln("1");
		} else if (i == 20) {
			pln("2");
			if (c == 'A'){
				pln("2-1");
			} else if (c == 'B'){
				pln("2-2");
			} else if (c == 'C'){
				pln("2-3");
			} else {
				pln("2-4");
			}
			pln("바깥쪽 switch문 밖");
		} 
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		D3 d = new D3();
		d.m1();
		// d.m2();
	}
}

class D4 {
	void m1() {
		int i = 30;
		char c = 'C';
		switch (i) {
			case 10:
				pln("1"); break;
			case 20:
				pln("2");
			switch (c){
				case 'A': pln("2-1"); break;
				case 'B': pln("2-2"); break;
				case 'C': pln("2-3"); break;
				default: pln("2-4");
			}
			pln("안쪽 switch 밖");
			break;
		case 30:
			pln("3");
			break;
		default:
			pln("4");
		}
		pln("바깥쪽 switch 밖");
	}

	void m2() {
		int i = 30;
		char c = 'C';
		if (i == 10) {
			pln("1");
		} else if (i == 20) {
			pln("2");
			if (c == 'A') {
				pln("2-1");
			} else if(c == 'B') {
				pln("2-2");
			} else if(c == 'C') {
				pln("2-3");
			} else {
				pln("2-4");
			}
			pln("안쪽 switch 밖");
		} else if (i == 30) {
			pln("3");
		} else {
			pln("4");
		}
		pln("바깥쪽 switch 밖");
	}

	void pln(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		D4 d = new D4();
		d.m1();
	}
}

class D5 {
	void m1() {
		int i = 20;
		char c = 'B';
		switch (i) {
			case 10: pln("1"); break;
			case 20:
				pln("2");
				switch (c){
					case 'A': pln("2-1"); break;
					case 'B': pln("2-2"); break;
					case 'C': pln("2-3"); break;
					default: pln("2-4");
				}
				pln("안쪽 switch 밖");
				break;
			case 30: pln("3"); break;
			default: pln("4");
		}
		pln("바깥쪽 switch 밖");
	}

	void m2() {
		int i = 20;
		char c = 'B';
		if (i==10){
			pln("1");
		} else if (i==20){
			pln("2");
			if (c == 'A'){
				pln("2-1");
			} else if (c == 'B'){
				pln("2-2");
			} else if (c == 'C'){
				pln("2-3");
			} else {
				pln("2-4");
			}
			pln("안쪽 switch 밖");
		} else if (i==30) {
			pln("3");
		} else {
			pln("4");
		}
		pln("바같쪽 switch 밖");
	}
	
	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		D5 d = new D5();
		d.m1();
		d.m2();
	}
}