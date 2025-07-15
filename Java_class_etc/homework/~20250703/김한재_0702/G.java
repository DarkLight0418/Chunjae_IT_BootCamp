public class G {
    public static void main(String[] args) {
        boolean flag = true;

        try {
            System.out.println("(1)");
            ExcepUser ex = new ExcepUser();
            System.out.println("(2)");
            if (flag) System.exit(-1);
            ex.m();
            System.out.println("(3)");
            ex.mm();
            System.out.println("(4)");
        } catch (MyException me) {
            System.out.println("(5)");
        } catch (YourException ye) {
            System.out.println("(6)");
        } catch (Exception e) {
            if(flag) return;
            System.out.println("(7)");
        } finally {
            System.out.println("(8)");
        }

        System.out.println("(9)");
    }
}
