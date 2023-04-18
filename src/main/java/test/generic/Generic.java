package test.generic;

class Member<T> {
    private T t;

    public Member(T t) {
        this.t = t;
    }

    public void dataType() {
        System.out.println("제너릭 클래스의 데이터 타입="+t.getClass().getName());
    }

    public <T> void genericMethod(T t) {
        System.out.println("제너릭 메소드 입니다. "+t.getClass().getName());
    }

}

public class Generic {
    public static void main(String[] args) {
        Member<String> member1 = new Member<>(new String());
        Member<StringBuilder> member2 = new Member<>(new StringBuilder());

        member1.dataType();
        member2.dataType();

        member1.genericMethod(new Integer(1));
        member2.genericMethod(new Double(1.1));
    }
}

