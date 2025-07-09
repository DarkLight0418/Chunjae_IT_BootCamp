class 학교 {
	String 학교이름;
	int 개교년도;
	int 교실수;
	int 선생님수;

	학교(String 학교이름, int 개교년도, int 교실수, int 선생님수) {
		this.학교이름 = 학교이름;
		this.개교년도 = 개교년도;
		this.교실수 = 교실수;
		this.선생님수 = 선생님수;
	}

	void 설립() {
		System.out.println("이 학교는 " + 개교년도 + "년에 지어졌습니다.");
	}
}

class 선생님 {
	학교 학교1;
	String 이름;
	String 직책;
	int 재직년수;

	선생님(String 이름, String 직책, int 재직년수) {
		this.이름 = 이름;
		this.직책 = 직책;
		this.재직년수 = 재직년수;
	}
	
	void 이_선생님은(학교 학교1) {
		System.out.println("이 선생님은 " + 학교1.학교이름 + "에 " + 재직년수 + "년 재직하고 계십니다.");
	}
}


class 학생 {
	학교 학교1;
	String 이름;
	int 학년;
	int 반;
	int 번호;

	학생(String 이름, int 학년, int 반, int 번호) {
		this.이름 = 이름;
		this.학년 = 학년;
		this.반 = 반;
		this.번호 = 번호;
	}	

	void 소속_확인(학교 학교1) {
		System.out.println(학교1.학교이름 + "에 " + 이름 + "이(가) 다닙니다.");
	}

	void 학교에_가다() {
		System.out.println(이름 + "이(가) 학교에 갑니다.");
	}

	void 수업을_듣다() {
		System.out.println("수업을 듣습니다.");
	} 

	학생 이_학생의_친구는(학생 학생1) {
		System.out.println(이름 + " 학생의 친구는 " + 학생1.이름 + "입니다.");
		return 학생1;
	}
}

class 오늘_학교 {
	void school1() {
		학교 schoolAA = new 학교("AA초등학교", 1994, 39, 40);
		System.out.println(schoolAA.학교이름);
		schoolAA.설립();

		선생님 teacher이 = new 선생님("이누구", "담임", 4);
		teacher이.이_선생님은(schoolAA);

		학생 student김 = new 학생("김누구", 3, 5, 5);
		System.out.println(student김.이름);
		student김.학교에_가다();		
		student김.소속_확인(schoolAA);

		학생 student신 = new 학생("신짱구", 3, 5, 15);
		student신.이_학생의_친구는(student김);
	}

	public static void main(String[] args) {
		오늘_학교 오늘1 = new 오늘_학교();
		오늘1.school1();
	}
}
