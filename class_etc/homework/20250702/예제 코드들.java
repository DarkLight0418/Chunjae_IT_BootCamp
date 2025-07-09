// 상속성 예제
/* 
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
		P.pln("s.power: " + sm.power);
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


// 다형성 예제

package day09;

import soo.P;

class Figure{
	void draw() {
		P.pln("도형을 그리다");
	}
}
class Circle extends Figure{
	void draw() {
		P.pln("원형을 그리다");
	}
}
class Rectangle extends Figure{
	void draw() {
		P.pln("직사각형 그리다");
	}
}
class Tri extends Figure{
	void draw() {
		P.pln("삼각형을 그리다");
	}
}

public class B {
	public static void main(String[] args) {
		Figure f1 = new Circle();
		Figure f2 = new Rectangle();
		Figure f3 = new Tri();
		f1.draw();
		f2.draw();
		f3.draw();
	}
}

// 은닉성 예제

package day09;

import soo.P;

class Account {
	private String ssn = "111111-2222222"; //계좌주
	String getSsn() {
		return ssn;
	}
	long balance = 10000L; //잔액
}

class Banker {
	public static void main(String[] args) {
		Account acc = new Account();
		
		String ssn1 = acc.getSsn(); 
		P.pln("ssn1: " + ssn1);
		P.pln("balance1: " + acc.balance);
		P.pln("");
		
		String ssn2 = acc.getSsn(); 
		P.pln("ssn2: " + ssn2);
		acc.balance = 20000L;
		P.pln("balance2: " + acc.balance);
		
	}
}

// 캡슐화 예제

package day09;

import soo.P;

class D {
	int sum(int i, int j) {
		return i+j;
	}
}

class DUser{ //사용자클래스
	public static void main(String[] args) {
		D d = new D();
		int r = d.sum(100, 200);
		P.pln("합: " + r);
	}
}

// try - catch 예외 처리 예제

package day09;

import java.io.*;

import soo.P;

public class E {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void m1() {
		try {
			P.pln("(1)");
			P.p("나이: ");
			String line = br.readLine();
			P.pln("(2)");
			int age = Integer.parseInt(line);
			P.pln("읽은 나이: " + age);
			P.pln("(3)");
		}catch(IOException ie) {
			P.pln("(4)");
		}catch(NumberFormatException ne) {
			P.pln("(5)");
			P.pln("숫자만 가능");
			m1();
		}
		
		P.pln("(6)");
	}
	public static void main(String[] args) {
		E e = new E();
		e.m1();
	}
}


*/