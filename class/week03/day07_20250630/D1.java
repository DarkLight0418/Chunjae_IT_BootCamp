package day07;
import soo.P;
public class D {
    
    int iss[][] = { { 1, 2 }, { 3, 4, 5 } }; // 2차원
	int isss[][][] = { iss, { { 60}, { 700,800 } },{ { 1000 } } }; // 3차원
	//isss[0][0][0];
    void out1() { // out1
		P.pln("iss[0][0]: " + iss[0][0]);
		P.pln("iss[0][1]: " + iss[0][1]);
		P.pln("iss[1][0]: " + iss[1][0]);
		P.pln("iss[1][1]: " + iss[1][1]);
	} 

	void out2() { // iss의 데이터 출력
		for (int k = 0; k < isss.length; k++) {
			for (int i = 0; i < iss[k].length; i++) {
				P.pln("iss[" + k + "][" + i + "] " + iss[k][i]);
			}
		}
	}
	void out3() { // isss의 데이터 출력
		for (int k = 0; k < isss.length; k++) {
		    for (int j = 0; j < isss[k].length; j++) {
				for (int i = 0; i < isss[k][j].length; i++) {
				     P.pln("isss[" + k + "][" + j + "][" + i + "]: " + isss[k][j][i]);
				}
			}
		}
	}
    public static void main(String args[]) {
        D d = new D();
	
		// d.out1();
		// d.out2();
        d.out3();
	}
}