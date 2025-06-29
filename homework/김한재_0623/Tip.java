class Tip {
	String str1 = "자장면";
	String str2 = new String("자장면");

	void test() {
		boolean b1 = str1.equals(str2);
		System.out.println("b1: " + b1);  //true

		boolean b2 = str2 == new String("자장면");
		System.out.println("b2: " + b2);  // false

		boolean b3 = str1 == "자장면";
		System.out.println("b3
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
