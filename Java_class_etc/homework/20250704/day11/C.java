package day11;

public class C { //Outter 
	int i=0;
	void m() {
		System.out.println("m()");
	}
	class CInner{ //Case1
		void use() {
			System.out.println("i: " + i);
			m();
		}
	}
//	C(){
//		new CInner(this).use();
//	}
}
//class CInner { //Case2
//	C c;
//	CInner(C c){
//		this.c = c;
//	}
//	void use() {
//		System.out.println("i: " + c.i);
//		c.m();
//	}
//}
