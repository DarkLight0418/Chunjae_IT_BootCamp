package day16_learning;

public class HYs2 {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = obj1;

        System.out.println(obj1.equals(obj2)); // 1 false -> 아예 다른 객체
        System.out.println(obj1.equals(obj3)); // 2 true -> 같은 객체

        // String equals()는 문자열 같은지만 비교하도록 오버라이딩됨

        String s1 = new String("hello");
        String s2 = new String("hello");
        String s3 = s1;

        System.out.println(s1.equals(s2)); // 3 true -> 문자열 같으니까
        System.out.println(s1 == s2);      // 4 false -> 아예 다른 객체
        System.out.println(s1.equals(s3)); // 5 true -> 같은 객체
        System.out.println(s1 == s3);      // 6 true -> 같은 객체
    }
}
