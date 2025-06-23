class B1 {
	byte b = 127;
	void m1() {
		System.out.println("b: " + (byte)(b+1));
	}
	void m2() {
		long lo = 10;
		int result = (int)(b + lo);
		System.out.println("result: " + result);
	}
	void m3() {
		float f = 123.12345678f;
		System.out.println("f: " + f);
		double d = 123.123456789012345;
		System.out.println("d: " + d);
	
		double r = f + d;
		System.out.println("r: " + r);
	}
	
	void m4() {
		long lo = 123456789012345L;
		//float f = 123.12345678f; 
		double d = 123.123456789012345;
	}

	void m5() {
		long lo = 1234567890123456789L;
		double d = lo;
		System.out.println("d: " + d);
	}
	
	public static void main(String[] args) {
		B1 b = new B1();
		// b.m1();
		// b.m2();
		// b.m3();
		// b.m4();
		b.m5();
	}
}

class B2 {
	byte b = 127;
	void m1() {
		System.out.println("b: " + (byte)(b+1));
	}

	void m2() {
		long lo = 10;
		int result = (int)(b + lo);
		System.out.println("result: " + result);
	}
	void m3() {
		float f = 123.12345678f;
		System.out.println("f: " + f);
		double d = 123.123456789012345;
		System.out.println("d: " + d);
	
		double r = f + d;
		System.out.println("r: " + r);
	}
	void m4() {
		long lo = 123456789012345L; 
		// float f = 123.12345678f; 
		double d = 123.123456789012345; 
		double r = lo + d;
		System.out.println("r: " + r);
	}
	void m5() {
		long lo = 1234567890123456789L; 
		double d = lo;
		System.out.println("d: " + d);
	}
	public static void main(String[] args) {
		B2 b = new B2();
		// b.m1();
		// b.m2();
		// b.m3();
		// b.m4();
		b.m5();
	}
}

class B3 {
	byte b = 127;
	void m1() {
		System.out.println("b: " + (byte)(b+1));
	}
	void m2() {
		long lo = 10;
		int result = (int)(b + lo);
		System.out.println("result: " + result);
	}
	void m3() {
		float f = 123.12345678f;
		System.out.println("f: " + f);
	}
	void m4() {
		long lo = 123456789012345L;
		float f = 123.12345678f;
		double d = 123.123456789012345;
		double r = lo + d;
		System.out.println("r: " + r);
	}

	void m5() {
		long lo = 1234567890123456789L;
		double d = lo;
		System.out.println("d: " + d);
	}

	public static void main(String[] args) {
		B3 b = new B3();
		// b.m1();
		// b.m2();
		// b.m3();
		// b.m4();
		b.m5();
	}
}

class B4 {
	byte b = 127;
	void m1() {
		System.out.println("b: " + (byte)(b+1));
	}
	void m2() {
		long lo = 10;
		int result = (int)(b + lo);
		System.out.println("result: " + result);
	}
	void m3() {
		float f = 123.12345678f;
		System.out.println("f: " + f);
		double d = 123.123456789012345;
		
		double r = f + d;
		System.out.println("r: " + r);
	
	}
	void m4() {
		long lo = 123456789012345L;
		// float f = 123.12345678f; 
		double d = 123.123456789012345; 
		double r = lo + d;
		System.out.println("r: " + r);
	}
	void m5() {
		long lo = 1234567890123456789L;
		double d = lo;
		System.out.println("d: " + d);
	}

	public static void main(String[] args) {
		B4 b = new B4();
		// b.m1();
		// b.m2();
		// b.m3();
		// b.m4();
		b.m5();
	}
}

class B5 {
	byte b = 127;

	void s1() {
		System.out.println("b: " + (byte)(b+1));
	}

	void s2() {
		long lo = 10;
		int result = (int)(b + lo);
		System.out.println("result: " + result);
	}

	void s3() {
		float f = 123.12345678f;  // float == 23412f;
		System.out.println("f: " + f);
		double d = 123.123456789012345;
		System.out.println("d: " + d);

		double r = f + d;
		System.out.println("r: " + r);
	}
	void s4() {
		long lo = 123456789012345L; // long == 2431521251L;
		// float f = 123.12345678f;
		double d = 123.123456789012345;
		double r = lo + d;
		System.out.println("r: " + r);
	}
	void s5() {
		long lo = 1234567890123456789L;
		double d = lo;
		System.out.println("d: " + d);

	}

	public static void main(String[] args) {
		B5 b = new B5();
		//b.s1();
		//b.s2();
		//b.s3();
		//b.s4();
		b.s5();
	}
}