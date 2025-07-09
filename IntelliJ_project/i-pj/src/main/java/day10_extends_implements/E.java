package day10_extends_implements;

abstract class E { //추상클래스  (붕어빵 틀에 구멍이 뚫렸어요!! -> 구현되지 않은 부분이 있어!)
	int i;
	E() {} 
	void m1() {	
		System.out.println("m1()");
	}
	abstract void m2(); //추상메소드 
}

class EChild extends E {
    void m2() {
        System.out.println("siuuuuuu");
    }
}

class EUser{
	public static void main(String args[]) {
		//E의 객체를 생성해서 사용하시요 ==> 다형성의 오묘함 "똑같은 메소드가 다양한 일을 해버리네?"
        E e = new EChild();
        System.out.println(e.i);
        e.m1();
        e.m2();
    }
}