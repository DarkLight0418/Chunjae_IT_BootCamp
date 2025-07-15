class B {
    void m1(){ //신사
        int i=0; //초기식 
        while(i<5) {//조건식
            System.out.println("i: " + i++); //증감식
        }
    }
    void m2(){ //조폭
        int i=0; //초기식
        do{
            System.out.println("i: " + i++); //증감식
        }while(i<5); //조건식 
    }
    void m3(){ //신사
        for(int i=0; i<5; i++){
            System.out.println("i: " + i);
        }
    }
    public static void main(String[] args) {
        B b = new B();
        //b.m1();
        //b.m2();
        b.m3();
    }
}

//초기식(밖), 조건식(안), 증감식(안)을 갖추어야 '유한루프'반복문이 됨 