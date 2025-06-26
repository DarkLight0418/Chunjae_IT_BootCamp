
//주제: 클래스의구조 + 실행흐름
package a.b; //1

import java.lang.*; //2

class A //3 
{
    String str = "A"; //4
    A(){ //5 
        System.out.println("2");
        m();
    }
    void m(){ //6
        System.out.println("3");
    }
    public static void main(String[] args) { //메소드
        System.out.println("1");
        new A();
        System.out.println("4");
    }
}

//주제: 조건문 
class B {
    void m1(){ //조건(if)
        boolean b = false;
        if(b){
            System.out.println("참");
        }else{
            System.out.println("거짓");
        }
    }
    void m2(){ //조건(switch)
        int i = 2;
        switch(i){
            case 1: System.out.println("1"); break;
            case 2: System.out.println("2"); break;
            default: System.out.println("1도 2도 아니다"); 
        }//
        System.out.println("switch out");
    }
    public static void main(String[] args) {
        B b = new B();
        //b.m1();
        b.m2();
    }
}

//주제: 반복문
class C {
    void m1(){//1
        int i = 0; //초기식
        while(i<3){ //조건식 
            System.out.println("i: " + i);
            i++; //증감식
        }
    }
    void m2(){//2
        int i = 0; //초기식
        do{
            System.out.println("i: " + i);
            i++; //증감식
        }while(i<3); //조건식 
    }
    void m3(){//3
        for(int i=0; i<3; i++){
            System.out.println("i: " + i);
        }
    }
    public static void main(String[] args) {
         C c = new C();
         //c.m1();
         //c.m2();
         c.m3();
    }
}

//주제: break
class D {
    void m1(){
        for(int i=0; i<5; i++){
            if(i==3) break;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }
    void m2(){
        for(int j=0; j<2; j++){
            for(int i=0; i<5; i++){
                if(i==3) break;
                System.out.println("i: " + i);
            }//1
            System.out.println("for의 밖1");
        }//2
        System.out.println("for의 밖2");
    }
    void m3(){
        aa:
        for(int j=0; j<2; j++){
            for(int i=0; i<5; i++){
                if(i==3) break aa;
                System.out.println("i: " + i);
            }//1
            System.out.println("for의 밖1");
        }//2
        System.out.println("for의 밖2");
    }
    void m4(){
        int i = 2;
        char c = 'A';
        bb:
        switch(i){
            case 1: System.out.println("1"); break;
            case 2: 
                switch(c){
                    case 'A': 
                        System.out.println("A"); break bb;
                    case 'B': 
                        System.out.println("B"); 
                }//1
                System.out.println("switch밖1");
                break;
            default: System.out.println("default");
        }//2
        System.out.println("switch밖2");
    }
    public static void main(String[] args) {
        D d = new D();
        //d.m1();
        //d.m2();
        //d.m3();
        d.m4();
    }
}

더보기
//주제: continue
class E {
    void m1(){
        for(int i=0; i<5; i++){
            if(i==2) continue;
            System.out.println("i: " + i);
        }
        System.out.println("for의 밖");
    }
    void m2(){
        cc:
        for(int j=0; j<2; j++){
            for(int i=0; i<5; i++){
                if(i==2) continue cc;
                System.out.println("i: " + i);
            }//
            System.out.println("for의 밖1");
        }//
        System.out.println("for의 밖2");
    }
    public static void main(String[] args) {
        E e = new E();
        //e.m1();
        e.m2();
    }
}

//주제: return
class F {
    F(){
        boolean b = false;
        System.out.println("1");
        if(b) return; //1
        System.out.println("2");
        m();
        System.out.println("3");
    }
    void m(){
        boolean b = true;
        System.out.println("4");
        if(b) return; //2
        System.out.println("5");
    }
    public static void main(String[] args) {
        System.out.println("0");
        F f = new F();
        boolean b = true;
        if(b) return;
        System.out.println("6");
    }
}

//주제: return 2
class G {
	void m1(){
		for(int i=0; i<5; i++){
			//if(i==2) break;
			//if(i==2) continue;
			if(i==2) return;
			System.out.println("i: " + i);
		}

		System.out.println("하나");
	}
	int m2(boolean b){
		if(b){
			System.out.println("1");
			return 1;
		}else{
			System.out.println("2");
			return -1;	
		}
	}
	public static void main(String[] args) {
		G g = new G();
		//g.m1();
		//System.out.println("둘");
		int result = g.m2(false);
		System.out.println("result: " + result);
	}
}

//주제: 연산자
class H {
    H(){
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        //m8();
        //m9();
        m10();
    }
    void m1(){ //1) 산술 ex) +, -, *, /, %
        double d = 1/2.0; //0.5
        System.out.println("d: " + d);

    int i = 5;
    int r = i%2;
    System.out.println("r: " + r);
	}
	void m2(){ //2) 증감 ex) ++,  -- 
		int i=0; 
		int j = (i++ + 10); // + = ++
		System.out.println("i: " + i + ", j: " + j);
	}
	void m3(){ //3) 대입 ex) = 
		int i = 2;
		System.out.println("i: " + i);
	}
	void m4(){ //4) 산술대입 ex) +=, -=, *=, /=, %=
		int i = 3; 
		i %= 2;
		System.out.println("i: " + i);
	}
	void m5(){ //5) 비트 ex) &, |, ~, 시프트( <<, >>, >>> )
		int i = 2<<2;
		System.out.println("i: " + i);
	}
	void m6(){//6) 비교 ex) ==, !=, <, <=, >, >=
		int i = 1;
		int j = 1;
		boolean b = i<=j;
		System.out.println("b: " + b);
	}
	void m7(){ //7) 논리 ex) &, &&, |, ||
		int i = 1;
		boolean b = true;
		boolean r = b || i++<2;
		System.out.println("r: " + r + ", i: " + i);
	}
	void m8(){ //8) 논리대입  ex) &=, |=
		boolean b = false;
		int i = 1;
		b |= (i>1);
		System.out.println("b: " + b);
	}
	void m9(){ //9) 조건 ex) A? B : C
		int i = 1;
		String r = (i<0)? "김치":"깍두기";
		System.out.println("r: " + r);    

		/*
		int i = 1;
		String r = null;
		if(i<0){
			r = "김치";
		}else {
			r = "깍두기";
		}
		System.out.println("r: " + r);*/
	}
	void m10() { //10) instanceof ex) 객체 instanceof 클래스 
		String str = "여행";
		boolean b = str instanceof String;
		System.out.println("b: " + b);
	}
	public static void main(String[] args) {
		new H();
	}
}

