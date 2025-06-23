class Hello  // 클래스 정의
{
	Hello() { // -> 생성자 정의
		System.out.print("한재가 ");
	}
	void go() {  // 메소드 정의
		System.out.println("간다");  // 한 문장의 끝 ;(세미콜론)
	}

	public static void main(String[] args) // 메소드 정의 (메인 메소드가 제일 먼저 실행됨)
	{
		new Hello().go();   // 메소드 호출


		/* new -> 새로운 거 만들어주세요; 
		   new Hello() -> Hello 객체 만들어주세요  
		   ()-> 동작, 행위 */

		// System.out.println("Hello 한재");
	}
}