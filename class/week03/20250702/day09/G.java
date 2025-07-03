package day09;

import soo.P;

public class G {
	public static void main(String[] args) {
		boolean flag = true;
		try { 
			P.pln("(1)");
			ExcepUser ex = new ExcepUser();
			P.pln("(2)");
			if(flag) System.exit(-1); //0:정상종료, -1:비정상종료
			ex.m();
			P.pln("(3)");
			ex.mm();
			P.pln("(4)");
		}catch(MyException me) {
			P.pln("(5)");
		}catch(YourException ye) {
			P.pln("(6)");
		}catch(Exception e) {
			if(flag) return;
			P.pln("(7)");
		}finally {
			P.pln("(8)");
		}
		
		P.pln("(9)");
	}
}
