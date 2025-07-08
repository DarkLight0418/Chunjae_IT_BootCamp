package day13.advanced;

public class User2 implements Comparable<User2> {
    private String name;
    private int age;
    public User2(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

    @Override
    public int compareTo(User2 o) {
        return Integer.compare(age, o.age);
    }
}