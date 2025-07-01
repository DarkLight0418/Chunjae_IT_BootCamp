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