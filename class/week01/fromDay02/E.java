class E {

	E() {
		pln("E 객체 생성 시작");
		pln("E 객체 생성 끝");  // 오로지 문자열만(pln 매개변수는 String(문자열이기에)
	}

	void pln(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		new E();
	}
}