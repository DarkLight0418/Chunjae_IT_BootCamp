// 인터페이스 A1.java
package com.example.day11;

public abstract interface A1 {
	public static final int I=2;
	public abstract void m1();
	
	public default void m2() {
		System.out.println("m2()");
	} 
	public static void m3() {
		System.out.println("m3()");
	}
}

// 인터페이스 A2.java
interface A2 extends A1 {
	void mm();
	void mmm();
}



