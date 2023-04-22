package test.equals_hashcode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.deepEquals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        Person minkai1 = new Person("밍아가", 3);
        Person minkai2 = new Person("밍아가", 31);

        System.out.println(minkai1.equals(minkai2));
        System.out.println("minkai1.hashCode()="+minkai1.hashCode());
        System.out.println("minkai2.hashCode()="+minkai2.hashCode());

        Hashtable<String, Integer> map = new Hashtable<>();
        map.put("밍아가", 33);
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("밍밍이", 31);
        Integer value = map2.put("밍밍이", 32);
        System.out.println(value);

        HashMap<String, Integer> map3 = new HashMap<>(100, 0.5f);
    }
}
