package day10;

class Woman {  //  부모 클래스
    public String name;
    public int age;

    public void info() {
        System.out.println("여자의 이름은 " + name + ", 나이는 " + age + "살입니다.");
    }
}

class Job extends Woman {  // Woman 클래스(부모 클래스)를 상속 받음
    String job;

    public void info() { // 부모 클래스에 있는 info() 메소드 재정의
        super.info();
        System.out.println("여자의 직업은 " + job + "입니다.");
    }
}

public class OverridingTest {
    public static void main(String[] args) {
        Job job = new Job();
    
        job.name = "유리";
        job.age = 30;
        job.job = "프로그래머";

        job.info();
    }
}