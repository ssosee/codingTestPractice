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

// S는 T와 구분하기 위해 다른 문자
class Person<T, S> {
    public T info;
    public S id;

    public Person(T info, S id) {
        this.info = info;
        this.id = id;
    }

    // 제너릭 메소드
    // 매개변수 데이터 타입을 확정하고 싶지 않아서 사용
    public <U> void printInfo(U info) {
        System.out.println(info);
    }
}

public class GenericDemo {
    public static void main(String[] args) {

        EmployeeInfo employeeInfo = new EmployeeInfo(1);
        Long id = 1L;
        // employeeInfo, id라는 매개변수를 통해 제너릭 생략 가능
        Person person = new Person<>(employeeInfo, id);

        person.<EmployeeInfo>printInfo(employeeInfo);
        // employeeInfo라는 매개변수를 통해 제너릭 생략 가능
        person.printInfo(employeeInfo.rank);

        // 제너릭 메소드의 타입 매개변수 우선 처리
        person.printInfo("이지금");
    }
}
