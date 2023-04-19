//import org.junit.jupiter.api.Test;
//
//import java.util.Collection;
//
//class MyGrandParent {}
//class MyParent extends MyGrandParent {}
//class MyChild extends MyParent {}
//
//public class WildCardTest {
//    // 와일드 카드의 최상위 타입을 MyParent로 설정했습니다.
//    public void printCollection(Collection<? extends MyParent> c) {
//        // 자식은 부모로 표현할 수 있다.
//        // c는 MyParent 또는 자식 클래스 이다.
//
//        // 컴파일 에러
//        // 자식(MyChild)은 부모로 표현할 수 없다.
//        for(MyChild e : c) {
//            System.out.println(e);
//        }
//
//        // 자식(MyParent)은 부모로 표현 가능하다.
//        for(MyParent e : c) {
//            System.out.println(e);
//        }
//
//        // 자식(MyParent)은 부모로 표현 가능하다.
//        for(MyGrandParent e : c) {
//            System.out.println(e);
//        }
//
//        // 자식(MyParent)은 부모로 표현 가능하다.
//        for(Object e : c) {
//            System.out.println(e);
//        }
//    }
//
//    // 와일드 카드의 최상위 타입을 MyParent로 설정했습니다.
//    public void addElement(Collection<? extends MyParent> c) {
//        // 부모는 자식을 저장할 수 있다.
//        // c는 MyParent 또는 MyParent의 자식 클래스인 것 까지만 알고 있다.
//
//        // c가 MyChild랑 관계 없는 MyParent의 자식클래스이면?
//        // 저장 불가
//        // 컴파일 에러
//        c.add(new MyChild());
//
//        // c가 MyParent의 자식클래스이면?
//        // 자식은 부모를 저장할 수 없다.
//        // 컴파일 에러
//        c.add(new MyParent());
//
//        // 자식은 부모를 저장할 수 없다.
//        // 컴파일 에러
//        c.add(new MyGrandParent());
//
//        // 자식은 부모를 저장할 수 없다.
//        // 컴파일 에러
//        c.add(new Object());
//    }
//
//    public void printAndAdd(Collection<MyParent> c) {
//        // 부모(MyGrandParent)는 자식(MyParent)을 담을 수 있다.
//        for(MyGrandParent e : c) {
//            System.out.println(e);
//        }
//
//        // 자식(MyChild)은 부모(MyParent)를 담을 수 없다.
//        for(MyChild e : c) {
//            System.out.println(e); // 컴파일 에러
//        }
//
//        // 부모(MyParent)는 자식(MyChild)을 담을 수 있다.
//        c.add(new MyChild());
//
//        // 자식(MyParent)은 부모(MyGrandParent)를 담을 수 없다.
//        c.add(new MyGrandParent()); // 컴파일 에러
//    }
//
//    // 와일드 카드의 최하위 타입을 MyParent로 설정했습니다.
//    public void printCollection(Collection<? super MyParent> c) {
//        // 자식은 부모로 표현할 수 있다.
//        // c는 MyParent 또는 MyParent의 부모 클래스 이다.
//
//        // 부모는 자식(MyChild)으로 표현될 수 없습니다.
//        // 컴파일 에러
//        for(MyChild e : c) {
//            System.out.println(e);
//        }
//
//        // c가 MyGrandParent라면?
//        // 부모는 자식(MyParent)으로 표현될 수 없습니다.
//        // 컴파일 에러
//        for(MyParent e : c) {
//            System.out.println(e);
//        }
//
//        // c가 Object라면?
//        // 부모는 자식(MyGrandParent)으로 표현될 수 없습니다.
//        // 컴파일 에러
//        for(MyGrandParent e : c) {
//            System.out.println(e);
//        }
//
//        // Object는 모든 객체의 부모이다.
//        for(Object e : c) {
//            System.out.println(e);
//        }
//    }
//
//    // 와일드 카드의 최하위 타입을 MyParent로 설정했습니다.
//    public void addElement(Collection<? super MyParent> c) {
//        // 부모는 자식을 저장할 수 있다.
//        // c는 MyParent 또는 부모 클래스 이다.
//
//        // 부모는 자식을 저장할 수 있다.
//        c.add(new MyChild());
//
//        // 자기 자신을 저장할 수 있다.
//        c.add(new MyParent());
//
//        // 만약 c가 MyParent이면?
//        // 자식은 부모를 저장할 수 없다.
//        // 컴파일 에러
//        c.add(new MyGrandParent());
//
//        // 컴파일 에러
//        c.add(new Object());
//    }
//
//    public void produce(Collection<? extends MyParent> c) {
//        for(MyGrandParent e : c) {
//            System.out.println(e);
//        }
//    }
//
//    public void consumer(Collection<? super MyParent> c) {
//        c.add(new MyChild());
//    }
//}
