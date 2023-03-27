import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ComparatorTest {

    @Test
    void 오름차순_테스트() {
        int[] A = {1, 2, 10, -5, 0, 8};
        Queue<Integer> queue = new PriorityQueue<>();

        for(int i : A) {
            queue.add(i);
        }

        System.out.println(queue);

        for(int i = 0; i < A.length; i++) {
            System.out.print(queue.poll()+" ");
        }
    }

    @Test
    void 내림차순_테스트() {
        int[] A = {1, 2, 10, -5, 0, 8};
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 > o2) {
                    return -1;
                } else if(o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });

        for(int i : A) {
            queue.add(i);
        }

        System.out.println(queue);

        for(int i = 0; i < A.length; i++) {
            System.out.print(queue.poll()+" ");
        }
    }

    @Test
    void ArraysContainsTest() {
        Integer[] A = {1, 2, 3};
        Assertions.assertEquals(true, List.of(A).contains(1));
    }
}
