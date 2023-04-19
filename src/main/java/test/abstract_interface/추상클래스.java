package test.abstract_interface;

abstract public class 추상클래스 {
    private String name;
    private int age;

    public 추상클래스(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class 자식클래스 extends 추상클래스 {

    public 자식클래스(String name, int age) {
        super(name, age);
    }
}
