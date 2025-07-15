class 붕어빵 {
    String 앙꼬; 
    int 가격;

    붕어빵(String 앙꼬, int 가격){
        this.앙꼬 = 앙꼬;
        this.가격 = 가격;
    }
    void 배부르게하다(){
        System.out.println("배부르게 합니다");
    }
    void 따스하게하다(){
        System.out.println("따스하게 합니다");
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
        System.out.println("앙꼬가 "+빵1.앙꼬+"이고 가격이 "+빵1.가격+"원이고, ");
        System.out.print("앙꼬가 "+빵2.앙꼬+"이고 가격이 "+빵2.가격+"원인 ");
        System.out.println("2개의 빵을 구었습니다");
    }
    붕어빵 판다(){
        return 빵1;
    }
}
class 아이 {
    붕어빵 빵;

int 돈 = 5000;
String 상태 = "배고픈";
붕어빵 산다(사장 몸빼주인){
    빵 = 몸빼주인.판다();
    System.out.println("붕어빵을 샀습니다");
    return 빵;
}
void 먹는다(){
    System.out.print("앙꼬가 "+빵.앙꼬+"이고, ");
    System.out.print("가격이 "+빵.가격+"인 붕어빵이 ");
    빵.배부르게하다();
}
}    

class 신 {
    public static void main(String[] args) {
        사장 몸빼주인 = new 사장("아주머니");
        System.out.print(몸빼주인.성별+"께서 ");
        몸빼주인.굽는다();

    아이 철수 = new 아이();
    System.out.print(철수.돈 + "원을 가진 "+철수.상태+" 아이가 ");
    붕어빵 빵 = 철수.산다(몸빼주인);
    철수.먹는다();
}
}

