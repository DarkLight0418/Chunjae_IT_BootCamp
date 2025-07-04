package day10;

abstract class E1 { //추상클래스
	int i;
	E1() {}
	void m1() {
		System.out.println("m1()");
	}
	abstract void m2(); //추상메소드
}

class E1Child extends E1 {
    void m2() {
        System.out.println("");
	}
	public static void main(String[] args) {

	}

}
