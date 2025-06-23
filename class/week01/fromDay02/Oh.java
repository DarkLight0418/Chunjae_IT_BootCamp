class Oh  // 클래스 선언
{
	String name;  // 멤버 변수(클래스 안에 선언한 변수) == 전역 변수
	int age = 20;  // 멤버 변수 2   // = : 대입 연산자

	Oh() {  // 생성자1 정의
		// System.out.println("Oh()");
		name = "홍길동";
		age = 30;
	}
	Oh(String n, int a) {  // 생성자2 정의  // 생성자는 파라미터만 바꿀 수 있음(이름은 언제나 같아야 함)
		name = n;
		age = a;
	}

	void m() {   // 메소드 정의
		System.out.println(name+"("+ age +")"+"이 달린다");
	}

	void m2(String n, int a) {
		System.out.println(n+"("+ a +")"+"이(가) 달린다");
	}

	public static void main(String[] args) {
		Oh oh = new Oh(); // 6줄
		Oh ohSA = new Oh("강감찬", 45);
		oh.m();
		ohSA.m();
		ohSA.m2("한재", 25);
	}
}