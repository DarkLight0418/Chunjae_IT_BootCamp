package day09;

import soo.P;

//상속성(Inheritance)
class Human {
	String name = "홍길동";
	Human(){}
	Human(String name){
		this.name = name;
	}
	void move() {
		P.pln("걷는다");
	}
}
class SuperMan extends Human {
	int power;
	SuperMan(String name, int power){
		super(name); 
		this.power = power;
	}
	void move() { //메소드 오버라이딩(재정의)
		P.pln("난다");
	}
}
class PepsiMan extends Human {
	int speed = 80;
	void move() {
		P.pln("달린다");
	}
}
class God {
	public static void main(String args[]) {
		Human m = new SuperMan("클락", 100); //자동형변환 
		P.pln("m.name: "+ m.name);
//		P.pln("m.power: " + m.power);
		m.move();
		P.pln("");
		
		SuperMan sm = (SuperMan)m; //강제형변환
		P.pln("sm.name: "+ sm.name);
		P.pln("sm.power: " + sm.power);
		sm.move();
		P.pln("");
		
		PepsiMan pm = new PepsiMan();
		P.pln("pm.name: "+ pm.name);
		P.pln("pm.speed: "+ pm.speed);
		pm.move();
		
		//PepsiMan pm2 = (PepsiMan)m; //런타임에러발생: 불가(원래의 형을 찾는 것이 아니기에 )
		//SuperMan sm2 = pm; //컴파일에러발생: 불가(형제관계) 
		
	}
}