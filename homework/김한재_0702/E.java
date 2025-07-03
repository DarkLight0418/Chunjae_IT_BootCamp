import java.io.*;

class E1 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    void m1() {
        try {
            System.out.println("(1)");
            System.out.print("나이: ");
            String line = br.readLine();
            System.out.println("(2)");
            int age = Integer.parseInt(line);
            System.out.println("읽은 나이: " + age);
            System.out.println("(3)");
        } catch (IOException ie) {
            System.out.println("(4)");
        } catch (NumberFormatException ne) {
            System.out.println("(5)");
            System.out.println("(6)");
            m1();
        }

        System.out.println("(6)");
    }

    public static void main(String[] args) {
        E1 e = new E1();
        e.m1();
    }
}
