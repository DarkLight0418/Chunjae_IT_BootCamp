package day09_20250702;

import soo.P;
// 상속성(Inheritance)
class Human {
	String name;
	// Human(){}
	
	Human(String name) {
		this.name = name;
	}
	
	void move() {
		System.out.println("걷는다.");
	}
}

class SuperMan extends Human {
	int power;
	SuperMan(String name, int power){
		super(name);  // 부모의 생성자 new Human(name);
		this.power = power;
	}
	
	void move() {  // 메소드 오버라이딩(재정의)
		P.pln("난다");
	}
}

class God {
	public static void main(String[] args) {
		Human m = new SuperMan("호날두", 100);  // 자동 형 변환
		P.pln("m.name : " + m.name);
//		P.pln("m.power : " + m.power);
		m.move();  // 근본적으로 Human이 아님
		
		P.pln("");
		
		SuperMan sm = (SuperMan) m;
		P.pln("m.name : " + sm.name);
		P.pln("m.power : " + sm.power);
		sm.move();  // 근본적으로 Human이 아님
	}
}
