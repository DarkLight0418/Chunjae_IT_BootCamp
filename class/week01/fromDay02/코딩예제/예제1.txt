class 붕어빵 {
    String 앙꼬; 
    int 가격;

    붕어빵(String 앙꼬, int 가격){
        this.앙꼬 = 앙꼬;
        this.가격 = 가격;
    }
    void 배부르게하다(){
        System.out.println("배부르게하다");
    }
    void 따스하게하다(){
        System.out.println("따스하게하다");
    }
}
class 사장 {
    붕어빵 빵1;
    붕어빵 빵2;
    String 성별;
    사장(String 성별){
        this.성별 = 성별;
    }
    void 굽는다(){
        빵1 = new 붕어빵("설탕", 500);
        빵2 = new 붕어빵("팥", 1000);
        System.out.println("2개의 빵을 구었습니다");
    }
    붕어빵 판다(){
        return 빵1;
    }
}

class 신 {
    public static void main(String[] args) {
        사장 주인 = new 사장("아주머니");
        System.out.print(주인.성별+" 사장님이 ");
        주인.굽는다();
        붕어빵 빵 = 주인.판다();
        System.out.print("앙꼬가 "+빵.앙꼬+"이고, 가격이 "+빵.가격+"원인 빵이 ");
        빵.배부르게하다();
        //빵.따스하게하다();
    }
}

