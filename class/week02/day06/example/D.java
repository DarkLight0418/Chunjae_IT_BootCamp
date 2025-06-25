class D {
    void m1(){ //2개 이상의 변수
        for(int i=0, j=5; i<j; i++, j--)
            System.out.println("i: " + i + ", j: " + j);
    }
    void m2(){ //1~100 짝수/홀수합
        int jjsum = 0;
        int holsum = 0;
        for(int i=0; i<=100; i++){
            if(i%2==1){
                holsum += i;
            }else {
                jjsum += i;
            }
        }
        System.out.println(
        "홀수합: "+holsum+", 짝수합: "+jjsum+" 전체합: "+(holsum+jjsum));
    }
    void m3(){ //별
        for(int j=0; j<4; j++){
            for(int i=0; i<4; i++){
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) 
    {
        D d = new D();
        //d.m1();
        d.m2();
        //d.m3();
    }
}