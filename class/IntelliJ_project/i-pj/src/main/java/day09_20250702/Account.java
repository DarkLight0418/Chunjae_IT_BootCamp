package day09_20250702;

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
