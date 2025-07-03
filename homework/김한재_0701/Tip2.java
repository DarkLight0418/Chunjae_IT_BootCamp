import java.util.*;

class Tip2 {
    Random r = new Random();
    int total = 13;

    void showRan() {
        int i = r.nextInt(total); // i : 0 ~ (total-1)
        System.out.println("i: " + i);
    }

    public static void main(String[] args) {
        new Tip2().showRan();
    }
}

class Tip2_2 {
    Random r = new Random();
    int total = 21;

    void showRan() {
        int i = r.nextInt(total);
        System.out.println("i: " + i);
    }

    public static void main(String[] args) {
        new Tip2_2().showRan();
    }
}