package self_review.exceptions;

public class ClassCastExceptionExample {
    public static void main(String[] args) {
        Dog dog = new Dog();
        changeDog(dog);

        Cat cat = new Cat();
        changeCat(cat);
    }

    public static void changeDog(Animal animal) {
        Dog dog = (Dog) animal;
    }
    public static void changeCat(Animal animal) {
        Cat cat = (Cat) animal;
    }
}

class Animal {}

class Dog extends Animal {}

class Cat extends Animal {}