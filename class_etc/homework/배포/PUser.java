import soo.P;

class PUser {
	void show1(){
		P.pln("���޷�");
	}
	void show2(){
		P.p("������");
	}
	public static void main(String[] args) {
		PUser user = new PUser();
		user.show1();
		user.show2();
	}
}

/*
	> javac PUser.java
	> java PUser
*/