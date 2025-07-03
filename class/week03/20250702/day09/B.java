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
