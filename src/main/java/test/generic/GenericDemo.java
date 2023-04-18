package test.generic;

class StudentInfo {
    public int grade;
    public StudentInfo(int grade) {
        this.grade = grade;
    }
}

class EmployeeInfo {
    public int rank;

    public EmployeeInfo(int rank) {
        this.rank = rank;
    }
}

class Person<T> {
    public T info;

    public Person(T info) {
        this.info = info;
    }
}

public class GenericDemo {
    public static void main(String[] args) {
        // Person에는 EmployeeInfo타입의 데이터만 들어오도록 한다.
        Person<EmployeeInfo> person1 = new Person<>(new EmployeeInfo(1));
        EmployeeInfo info1 = person1.info;
        System.out.println(info1.rank);

        Person<String> person2 = new Person<>("사장");
        String info2 = person2.info;
        // System.out.println(info2.rank); // 컴파일 에러
    }
}
