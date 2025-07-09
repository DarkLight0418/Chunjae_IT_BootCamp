public class B {
	int score = 85;
	
	void m1() {
		if (score >= 90) {
			pln("A");
		} else if (score >= 80) {
			pln("B");
		} else if (score >= 70) {
			pln("C");
		} else if (score >= 60) {
			pln("D");
		} else if (score >= 50) {
			pln("F");
		}
	} // 좀 지저분해보임, 괄호가 많아 가독성이 떨어짐
	  // 다만 다 사용할 수 있는 장점이 있음

	void m2() {
		score = score / 10;
		switch (score) {  // byte, short, char, int (여기까지 기본형 4개) String (얘까지 5개)
			case 9: pln("A"); break;
			case 8: pln("B"); break;  // break; 더이상 아래로 수행되지 않음
			case 7: pln("C"); break;
			case 6: pln("D"); break;
			default: pln("F");  // break문 굳이 안써도 됨.(위로 올라간다면 써줄 필요가 있음)
			// default문은 모든 case 다 검사한 후 들어오는 문임. 그래서 마지막에 쓰는 게 좋음
		}
		// 특수한 케이스에서 가능

		pln("switch 탈출");
	}

	void pln(String str) {
		System.out.println(str);
	}
	public static void main(String[] args) {
		B b = new B();
		b.m2();
	}
}