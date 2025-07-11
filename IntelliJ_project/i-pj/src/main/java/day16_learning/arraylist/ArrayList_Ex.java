package day16_learning.arraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayList_Ex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("이름을 입력해주시와요 : ");
            names.add(sc.nextLine());
        }

        System.out.println("성이 '김'인 분의 이름을 모두 출력합니다.");

        for(int i=0; i<=4; i++) {
            String name = names.get(i);
            if (name.startsWith("김")) {
                index++;
                System.out.printf("[%s]\t", names.get(i));
            }
        }
        System.out.println();
        System.out.println("김씨 성을 가진 분들은 모두 " + index + "명입니다.");
    }
}
