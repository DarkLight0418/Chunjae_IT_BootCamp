package day12;

import soo.P;

interface FI{
    void m(int m);
}
class F {
    private int i = 1; //멤버
    void use1(int j) { //지역(파)
        int k = 3; //지역(선초)
        class FIChild implements FI {
            public void m(int m) {
                int n = 4;
                P.pln("FI의 m()구현(1) i:"+i+", j:"+j+", k:"+k +", n:"+n+ ", m:" + m);
            }
        }

        FI fi = new FIChild();
        fi.m(5);
    }
    void use2(int j) {
        int k = 3;
        FI fi = (m) -> {
            int n = 4;
            P.pln("FI의 m()구현(2) i:"+i+", j:"+j+", k:"+k +", n:"+n + ", m:" + m);
        };

        fi.m(5);
    }
    public static void main(String args[]) {
        F f = new F();
        //f.use1(2);
        f.use2(2);
    }
}
