package toss_assistant_algorithm_study.season3.week2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.stream.Collectors;

public class 두_큐_합_같게_만들기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
        System.out.println(solution.solution(new int[]{1, 1, 1, 8, 10, 9}, new int[]{1, 1, 1, 1, 1, 1}));
    }

    /**
     * 길이가 같은 queue1, queue2
     * 각 1회의 pop, insert가 1번 수행
     *
     * queue1 = [3, 2, 7, 2] -> 14
     * queue2 = [4, 6, 5, 1] -> 16
     * queue1 + queue2 = 30 -> queue1, queue2 = 15
     *
     * queue1 = [2, 7, 2, 4] -> 15
     * queue2 = [6, 5, 1, 3] -> 15
     *
     *
     * 아이디어
     *  - 큐의 성질을 이용한다.
     *  - q1의 합이 더 큰 경우 q2에서 하나 빼고, q1에 넣어준다.
     *  - q2의 합이 더 큰 경우 q1에서 하나 빼고, q2에 넣어준다.
     *
     * 자료구조
     *  - Queue
     *
     * 시간 복잡도
     *  - O(N)
     */
    static class Solution {

        public int solution2(int[] queue1, int[] queue2) {
            long q1Sum = Arrays.stream(queue1).sum();
            long q2Sum = Arrays.stream(queue2).sum();
            long sum = q1Sum + q2Sum;

            // 두 큐의 합을 같게 만들 수 없음
            if(sum % 2 != 0) return -1;

            Queue<Integer> q1 = Arrays.stream(queue1)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayDeque::new));

            Queue<Integer> q2 = Arrays.stream(queue2)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayDeque::new));

            int count = 0;
            while (q1Sum != q2Sum) {
                // 모든 큐를 순회했을 경우(두 큐 사이즈보다 큰 경우)
                if(count > queue1.length * 3) {
                    return -1;
                }
                // q1의 합이 더 큰 경우
                // q2에서 하나 빼고, q1에 넣어준다.
                else if(q1Sum > q2Sum) {
                    Integer poll = q1.poll();
                    q2.add(poll);

                    q1Sum -= poll;
                    q2Sum += poll;
                }
                // q2의 합이 더 큰 경우
                // q1에서 하나 빼고, q2에 넣어준다.
                else if(q1Sum < q2Sum) {
                    Integer poll = q2.poll();
                    q1.add(poll);

                    q1Sum += poll;
                    q2Sum -= poll;
                }
                else {
                    return count;
                }
                // 카운트 증가
                count++;
            }

            return count;
        }

        public int solution(int[] queue1, int[] queue2) {
            long q1Sum = Arrays.stream(queue1).sum();
            long q2Sum = Arrays.stream(queue2).sum();

            if((q1Sum + q2Sum) % 2 != 0) return -1;

            Queue<Integer> q1 = Arrays.stream(queue1)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayDeque::new));

            Queue<Integer> q2 = Arrays.stream(queue2)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayDeque::new));

            int count = 0;
            while (true) {
                if(count > queue1.length * 3) return -1;
                else if(q1Sum > q2Sum) {
                    Integer poll = q1.poll();
                    q2.offer(poll);

                    q1Sum -= poll;
                    q2Sum += poll;
                } else if(q1Sum < q2Sum) {
                    Integer poll = q2.poll();
                    q1.offer(poll);

                    q1Sum += poll;
                    q2Sum -= poll;
                } else {
                   return count;
                }
                count++;
            }
        }
    }
}
