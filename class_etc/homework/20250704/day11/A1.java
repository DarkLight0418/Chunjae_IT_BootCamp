package day11;

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


