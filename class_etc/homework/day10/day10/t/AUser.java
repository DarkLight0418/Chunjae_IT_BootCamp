package day10.t;

import day10.A;

class AUser extends A {
	AUser(){
		super(); //new A(); 
	}
	void use() {
		System.out.println("i: " + i);
		m();
	}
	public static void main(String args[]) {
		new AUser().use();
	}
}