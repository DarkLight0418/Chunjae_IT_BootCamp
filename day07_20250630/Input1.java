
class Input1 {

    void m(String[] args) {
        if(args.length == 0) {
            System.out.println("사용법: java Input1 과목1, 과목2, ...");
            return;
        }
        for (int i=0; i<args.length; i++) {
            System.out.println("args[" + i + "]" + args[i]);
        }
    }
    
    public static void main(String[] args) {
        /* 
        System.out.println("args.length: " + args.length);
        for(int i=0; i<args.length; i++) {
            System.out.println("args["+i +"] " +  args[i]);
        }
            */
        new Input1().m(args);
    }
}