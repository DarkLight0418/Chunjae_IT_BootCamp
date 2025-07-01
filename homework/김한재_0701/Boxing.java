public class Boxing {
    int i = 10;

    void m() {
        //Integer iObj = new Integer(i);
        Integer iObj = i;

        int ii = iObj;

        System.out.println("ii: " + ii);
    }
    public static void main(String[] args) {
        Boxing b = new Boxing();
        b.m();
    }
}
class Boxing2 {
    int i = 10;

    void m() {
        //Integer iObj = new Integer(i);  // 왜 new Integer를 사용할 수 없다는 오류가 뜰까
        Integer iObj = i;

        int ii2 = iObj;

        System.out.println("ii2: " + ii2);
    }
    public static void main(String[] args) {
        Boxing2 b = new Boxing2();
        b.m();
    }
}