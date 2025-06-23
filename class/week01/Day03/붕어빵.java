class 붕어빵 {
	String 앙꼬 = "팥";  // (== String 앙꼬 = new String("팥");)
	int 가격 = 400;
	// 변수 선언 

	/* 붕어빵(){} */ 
	/* 지역 변수(지역 안에 선언된 변수)가 전역 변수보다 더 강함.  */
	붕어빵(String 앙꼬, int 가격) {  // 붕어빵(매개 변수); 매개 변수 = 매개 변수;
		this.앙꼬 = 앙꼬;
		this.가격 = 가격;  // 앞에 this를 써줘야 붕어빵 메소드에 있는 게 적용됨.
	}

	void 배부르게$하다() {
		System.out.println("배부르게 해줍니다");
	}
}

// 붕어빵 생성자가 이미 존재하면 기본 생성자는 안 만들어짐!!



class 사장 {
	붕어빵 빵1;
	붕어빵 빵2;
	String 성별;
	사장(String 성별) {
		this.성별 = 성별;
	}

	void 굽는다() {
		System.out.print("노릇노릇 ");
		빵1 = new 붕어빵("설탕", 300);
		빵2 = new 붕어빵("팥", 400);

		System.out.println("앙꼬가 " + 빵1.앙꼬 + "이고 가격이 " + 빵1.가격 + "원이고, ");
		System.out.print("앙꼬가 " + 빵2.앙꼬 + "이고 가격이 " + 빵2.가격 + "원인 ");
		System.out.println("2개의 빵을 구웠습니다");
	}
	붕어빵 판다() {
		return 빵1;
		}
	}
	
class 아이 {
	붕어빵 빵;
	// 먹는다()에서 쓸 수 있게끔 전역 변수로 설정
	int 돈;
	String 상태;
	String 이름;

	아이(String 이름, String 상태, int 돈) {
		this.이름 = 이름;
		this.상태 = 상태;
		this.돈 = 돈;

		// 현재 만든 생성자에 의해 무조건 멤버 변수는 바뀌게 됨
	}

	붕어빵 산다(사장 주인1) {
		빵 = 주인1.판다();
		System.out.println("붕어빵을 샀습니다");
		return 빵;
	
		// return 주인1.판다();
	}

	void 먹는다() {
		System.out.print("앙꼬가 " + 빵.앙꼬 + "이고, ");
		System.out.print("가격이 " + 빵.가격 + "원인 붕어빵을 통해 ");
		빵.배부르게$하다();
	}
}

class 신 {
	// 신() {} (숨겨짐(없어도 자동생성))
	
	void make1() {
		사장 주인1 = new 사장("아주머니");
		System.out.print(주인1.성별 +"께서 ");
		주인1.굽는다();

		아이 손님1 = new 아이("철수", "배고픈", 10000);
		System.out.print(손님1.돈 + "원을 가진 " + 손님1.상태 + " " + 손님1.이름 + "가 ");
		붕어빵 빵1 = 손님1.산다(주인1);
		손님1.먹는다();
	}

	void make2() {
		사장 주인2 = new 사장("아저씨");
		System.out.print(주인2.성별 +"께서 ");
		주인2.굽는다();
		
		아이 손님2 = new 아이("영희", "가여운", 15000);
		System.out.print(손님2.돈 + "원을 가진 " + 손님2.상태 + " " + 손님2.이름 + "가 ");
		붕어빵 빵2 = 손님2.산다(주인2);
		손님2.먹는다();
	}

	public static void main(String[] args) {
		신 god = new 신();
		god.make1();
		System.out.println();
		god.make2();
	}
}

// 멋진 사나이 => 사나이.모습; 멋진 사나이 잔다 => 사나이.잔다();
			// 주체.특성		or			주체.메소드