package day09;

import soo.P;

class MyException extends Exception {
	String name = "나의예외";
	void doIt1() {
		System.out.println("doIt1()");
	}
}
class YourException extends Exception {
	String name = "너의예외";
	void doIt2() {
		System.out.println("doIt2()");
	}
}

class ExcepUser {
	boolean flag1;
	boolean flag2;
	boolean flag3 = true;
	
	ExcepUser() throws MyException {
		if(flag1){
			throw new MyException();
		}
	}
	void m() throws YourException {
		if(flag2){
			throw new YourException();
		}
	}
	void mm() throws Exception {
		if(flag3) {
			throw new Exception();
		}
	}
}

public class F {
	public static void main(String[] args) throws Exception {
		try {
			P.pln("(1)");
			ExcepUser ex = new ExcepUser();
			P.pln("(2)");
			ex.m();
			P.pln("(3)");
			ex.mm();
			P.pln("(4)");
		}catch(MyException me) {
			me.doIt1();
			System.out.println(me.name);
			P.pln("(5)");
		}catch(YourException ye) {
			ye.doIt2();
			System.out.println(ye.name);
			P.pln("(6)");
		}
		/*catch(Exception e) {
			System.out.println(e.toString()); //java.lang.Exception
			P.pln("(7)");
		}*/
		
		P.pln("(8)");
	}
}
