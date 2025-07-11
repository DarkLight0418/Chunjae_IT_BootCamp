package day16_learning.arraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayList_Ex02 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> nicknames = new ArrayList<>();
        int[] lengthArr = new int[5];
        int max = lengthArr[0];  // 최대값 저장

        for(int i = 0; i <= 4; i++) {
            System.out.print("별명을 입력해주세요!!: ");
            nicknames.add(sc.nextLine());
        }

        for(int i = 0; i <= 4; i++) {
            lengthArr[i] = nicknames.get(i).length();
            if (max < lengthArr[i]) {
                max = lengthArr[i];
            }
        }

        for(int i = 0; i <= 4; i++) {
            if(max == nicknames.get(i).length()) {
                System.out.println("가장 길이가 긴 별명은 > " + nicknames.get(i));
            }
        }

    }
}
