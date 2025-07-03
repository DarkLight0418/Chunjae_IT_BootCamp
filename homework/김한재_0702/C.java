class Account1 {
    private String ssn = "111111-2222222";
    String getSsn() {
        return ssn;
    }
    long balance = 10000L;
}


class Banker1 {
    public static void main(String[] args) {
        Account1 acc = new Account1();
        
        String ssn1 = acc.getSsn();
        System.out.println("ssn1: " + ssn1);
        System.out.println("balance1: " + acc.balance);
        System.out.println("");

        String ssn2 = acc.getSsn();
        System.out.println("ssn2: " + ssn2);
        acc.balance = 2000000L;
        System.out.println("balance2: " + acc.balance);
    }
}

class Account2 {
    private String ssn = "333333-4444444";
    String getSsn() {
        return ssn;
    }
    long balance = 200000L;
}

class Banker2 {
    public static void main(String[] args) {
        Account2 acc = new Account2();

        String ssn1 = acc.getSsn();
        System.out.println("ssn1: " + ssn1);
        System.out.println("balance1: " + acc.balance);
        System.out.println("");

        String ssn2 = acc.getSsn();
        System.out.println("ssn2: " + ssn2);
        acc.balance = 2000000L;
        System.out.println("balance2: " + acc.balance);
    }
}
