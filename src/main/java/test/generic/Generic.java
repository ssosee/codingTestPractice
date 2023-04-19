package test.generic;

import java.util.ArrayList;
import java.util.List;

class Admin {}
class Member<T extends Admin> {
    List<T> list = new ArrayList<>();

    public void add(T t) {
        list.add(t);
    }

    public T get(int index) {
        return list.get(index);
    }
}

public class Generic {
    public static void main(String[] args) {
        Member<Admin> adminMember = new Member<>();
        adminMember.add(new Admin());
        adminMember.get(0);
        System.out.println(adminMember);
    }
}

