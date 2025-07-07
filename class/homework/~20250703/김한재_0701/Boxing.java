class Boxing1 {
    int i = 10;

    void m() {
        //Integer iObj = new Integer(i);
        Integer iObj = i;

        int ii = iObj;

        System.out.println("ii: " + ii);
    }
    public static void main(String[] args) {
        Boxing1 b = new Boxing1();
        b.m();
    }
}
<<<<<<< HEAD

class Boxing2 {
    int i = 15;

    void m() {
        Integer iObj = i;

        int ii = iObj;

        System.out.println("ii: " + ii);
    }
    
=======
class Boxing2 {
    int i = 10;

    void m() {
        //Integer iObj = new Integer(i);  // 왜 new Integer를 사용할 수 없다는 오류가 뜰까
        Integer iObj = i;

        int ii2 = iObj;

        System.out.println("ii2: " + ii2);
    }
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
    public static void main(String[] args) {
        Boxing2 b = new Boxing2();
        b.m();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 129055d561502c06d23b22e9cb327a8524d34fd0
