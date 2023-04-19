package test.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

abstract class Info {
    public abstract int getLevel();
}

class EmployeeInfo extends Info {
    public int rank;

    public EmployeeInfo(int rank) {
        this.rank = rank;
    }

    @Override
    public int getLevel() {
        return this.rank;
    }
}

// T로 올 수 있는 데이터 타입을 Info또는 Info의 자식 클래스로 제한
class Person<S, T extends Info> {
    public T info;
    public S id;

    public Person(T info, S id) {
        this.info = info;
        this.id = id;
        // T를 Info로 제한했기 때문에 사용 가능
        info.getLevel();
    }

    // 제너릭 메소드
    // 매개변수 데이터 타입을 확정하고 싶지 않아서 사용
    public <U> void printInfo(U info) {
        System.out.println(info);
    }
}

public class GenericDemo {
    public static void main(String[] args) {

        // EmployeeInfo는 Info의 자식 입니다.
        Person<Long, EmployeeInfo> person = new Person<>(new EmployeeInfo(1), 1L);
        // String 은 Info의 자식이 아닙니다.
        // 컴파일 에러
        // Person<Long, String> stringPerson = new Person<Long, String>("이지동");

    }

    static void printCollection(Collection<?> c) {
        for(Object o : c) {
            System.out.println(o);
        }
    }
}
