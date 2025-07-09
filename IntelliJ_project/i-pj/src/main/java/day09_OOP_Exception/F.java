package day09_OOP_Exception;

import soo.P;

class MyException extends Exception {
	String name = "나의 예외";
	
	void doIt() {
		System.out.println("doIt()");
	}
}

class YourException extends Exception {
	String name = "너의 예외";
	
	void doIt2() {
		System.out.println("doIt2()");
	}
}

class ExcepUser {
	boolean flag1;
	boolean flag2 = true;
	ExcepUser() throws MyException {
		if(flag1) {
			throw new MyException();
		}
	}
	
	void m() throws YourException {
		if(flag2) {
			throw new YourException();
		}
	}
}

public class F {
	public static void main(String[] args) {
		try {
			P.pln("(1)");
			ExcepUser ex = new ExcepUser();
			P.pln("(2)");
			ex.m();
			P.pln("(3)");
		} catch (MyException me) {
			P.pln("(4)");
		} catch (YourException ye) {
			P.pln("(5)");
		}
		P.pln("(6)");
	}

}
