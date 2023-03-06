import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test1 {

    @Test
    void arrayTest() {
        int[] array = new int[4];
        array[0] = 1; // 인덱스 0에 1 저장
        array[1] = 2; // 인덱스 1에 2 저장
        array[2] = 3; // 인덱스 2에 3 저장

        /**
         * 1과 2 사이에 100이라는 숫자를 넣고 싶다면?
         * -> 인덱스1 부터 오른쪽으로 한칸씩 이동시킨다.
         */
        array[3] = array[2];
        array[2] = array[1];
        array[1] = 100;

        for (int i = 0; i < array.length; i++) {
            System.out.println("array["+i+"]="+array[i]);
        }
    }

    @Test
    void arrayListTest() {
        LinkedList<Integer> arrayList = new LinkedList<>();
        arrayList.add(100);
        arrayList.add(50);
        arrayList.add(200);
        arrayList.add(30);
        arrayList.forEach(System.out::println);
    }
}
