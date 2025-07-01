class 붕어빵 { 
    String 앙꼬; 
    int 가격;

    붕어빵(String 앙꼬, int 가격){
        this.앙꼬 = 앙꼬;
        this.가격 = 가격;
    }
    void 배부르게하다(){
        System.out.println("배부르게 합니다");
    }
}
class 사장 {
    붕어빵 빵1;
    붕어빵 빵2;
    String 성별;
    사장(String 성별){
        this.성별 = 성별;
    }
    void 굽는다(){
        빵1 = new 붕어빵("설탕", 500);
        빵2 = new 붕어빵("팥", 1000);
        System.out.println("앙꼬가 "+빵1.앙꼬+"이고 가격이 "+빵1.가격+"원이고, ");
        System.out.print("앙꼬가 "+빵2.앙꼬+"이고 가격이 "+빵2.가격+"원인 ");
        System.out.println("2개의 빵을 구었습니다");
    }
    붕어빵 판다(){
        return 빵1;
    }
}
class 아이 {
    붕어빵 빵;
    int 돈;
    String 상태;
    String 이름;

	아이(String 이름, String 상태, int 돈){
		this.이름 = 이름;
		this.상태 = 상태;
		this.돈 = 돈;
	}
	
	붕어빵 산다(사장 주인1) {
		빵 = 주인.판다();
		System.out.println("붕어빵을 샀습니다");
		return 빵;
	}

	void 먹는다(){
		System.out.print("앙꼬가 "+빵.앙꼬+"이고, ");
		System.out.print("가격이 "+빵.가격+"인 붕어빵이 ");
		빵.배부르게하다();
	}
}    

class 신 {
    void make1(){
        사장 주인1 = new 사장("아주머니");
        System.out.print(주인1.성별+"께서 ");
        주인1.굽는다();

		아이 손님1 = new 아이("철수", "배고픈", 5000);
		System.out.print(손님1.돈 + "원을 가진 "+손님1.상태+" "+손님1.이름+"가 ");
		붕어빵 빵1 = 손님1.산다(주인1);
		손님1.먹는다();
	}
	void make2(){
		사장 주인2 = new 사장("아저씨");
		System.out.print(주인2.성별+"께서 ");
		주인2.굽는다();

		아이 손님2 = new 아이("영희", "가여운", 10000);
		System.out.print(손님2.돈 + "원을 가진 "+손님2.상태+" "+손님2.이름+"가 ");
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