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

class Boxing2 {
    int i = 15;

    void m() {
        Integer iObj = i;

        int ii = iObj;

        System.out.println("ii: " + ii);
    }
    
    public static void main(String[] args) {
        Boxing2 b = new Boxing2();
        b.m();
    }
}
