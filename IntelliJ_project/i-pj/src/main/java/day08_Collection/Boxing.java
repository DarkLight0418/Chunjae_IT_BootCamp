package day08_Collection;

import soo.P;

public class Boxing {
    int i = 10;
    void m() {
        //Integer iObj = new Integer(i);
        Integer iObj = i; //AutoBoxing : int -> Integer

        //int ii = iObj.intValue();
        int ii = iObj; //UnBoxing : Integer -> int

        P.pln("ii: " + ii);
    }
    public static void main(String[] args) {
        Boxing b = new Boxing();
        b.m();
    }
}
