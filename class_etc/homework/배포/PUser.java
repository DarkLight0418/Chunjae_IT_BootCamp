import soo.P;

class PUser {
	void show1(){
		P.pln("진달래");
	}
	void show2(){
		P.p("개나리");
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