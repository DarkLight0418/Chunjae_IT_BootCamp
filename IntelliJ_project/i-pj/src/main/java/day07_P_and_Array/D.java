package day07_P_and_Array;

import soo.P;

public class D {
    int is1[] = {1, 2};
    int is2[] = {3, 4, 5};
    int iss[][] = { is1, is2 };

    int isss[][][] = { iss, { {60} }, { {700, 800 } }, { { 1000 } } };  // 3차원

    /*
    iss[0] == {1, 2}
    iss[1] == {3, 4, 5}
     */

    // int iss[][] = { {1, 2}, {3, 4, 5} };  // 2차원

    void out2() {
        // 바깥쪽 for문: 2차원 배열의 "행(row)"을 순회
        for (int j = 0; j < iss.length; j++) {
            
            // 안쪽 for문: 해당 행의 "열(column)"을 순회
            for (int i = 0; i < iss[j].length; i++) {
                /* iss[j] : j번째 행 전체
                예를 들어 iss = {{1, 2}, {3, 4, 5}}라면:

                    iss[0]는 {1, 2}
                    iss[1]는 {3, 4, 5}
                ------------------------------
                    iss[j].length

                    해당 행에 포함된 열(column)의 개수를 나타냅니다.

                    예:
                    iss[0].length == 2
                    iss[1].length == 3
                */
                
                // 현재 요소의 인덱스와 값을 출력
                // 예: iss[0][1]: 5 와 같은 형식으로 출력
                P.pln("iss[" + j + "][" + i + "]: " + iss[j][i]);
            }
        }
    }

    void out3() {
        for(int k = 0; k < isss.length; k++) {
            for(int j = 0; j < isss[k].length; j++) {
                for(int i = 0; i < isss[k][j].length; i++) {
                    P.pln("isss[" + k + "][" + j + "][" + i + "]: " + isss[k][j][i]);
                }
            }
        }
    }
    public static void main(String[] args) {
        D d = new D();
        d.out3();
    }
}
