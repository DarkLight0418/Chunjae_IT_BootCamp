package gui.day01_self;
import java.util.ArrayList;
import java.util.Scanner;

public class YoonS {
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> result = new ArrayList<>();
    int sum = 0;

    void calcul() {
        while (sc.hasNextLine()) {
            String number = sc.nextLine();
            if (number.trim().isEmpty()) {
                end();
                break;
            }

            String parts[] = number.split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            result.add(a + b);
        }
    }


    void end() {
        for (int c : result) {
            sum += c;
        }
        System.out.println(sum);
    }


    public static void main(String args[]) {
        YoonS num = new YoonS();
        num.calcul();
    }
}
