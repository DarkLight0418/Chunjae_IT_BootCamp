package self_review.nn;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        System.out.println("주사위 던지기!!");
        Random r = new Random();
        int a = r.nextInt(1, 7);
        System.out.println("나온 숫자 : " + a);
    }
}
