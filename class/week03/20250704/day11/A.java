package day11;

public interface A extends A1, A2 {
	void mmm();
	public default void m2() { //오버라이딩 가능 
		System.out.println("m22()");
	} 
}
