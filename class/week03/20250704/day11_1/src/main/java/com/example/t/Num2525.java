import java.util.Scanner;

class Num2525{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        int c = sc.nextInt();
        for(int n=1;n<=16;n++){
            if(a>23 || a<0 || b<0 || b>59) {
                return;
            }
            if((b+c)<60) System.out.println(a+ " " + (b+c));
            if((b+c)>=60*n && (b+c)<120 )  {
                System.out.println(a+1+ " " + (b+c-60*n));
            }
            if((b+c)>=1020 && c<1000 && (b+c)<1060) 
                System.out.println(a+16+ " " + (b+c-1020));
            
            if(c>1000) {
                return;
            }
        }
    }
}