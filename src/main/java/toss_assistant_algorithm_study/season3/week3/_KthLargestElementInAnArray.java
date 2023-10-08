package toss_assistant_algorithm_study.season3.week3;

import java.util.PriorityQueue;
import java.util.Queue;

public class _KthLargestElementInAnArray {
    public static void main(String[] args) {

    }

    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            /**
             * k번째로 큰 숫자를 반환
             *
             * 아이디어
             * - 우선순위 큐
             */
            // 내림차순
            Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
            for(Integer i : nums) {
                pq.offer(i);
            }

            int answer = 0;
            for(int i = 0; i < k; i++) {
                answer = pq.poll();
            }

            return answer;
        }
    }
}
