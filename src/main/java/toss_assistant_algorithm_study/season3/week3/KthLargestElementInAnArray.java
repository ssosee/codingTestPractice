package toss_assistant_algorithm_study.season3.week3;

import java.util.*;

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    /**
     * 아이디어
     * - 배열이 있을 때, 배열에서 k번째로 큰 수를 반환
     *
     * 자료구조
     * - 우선순위큐
     *
     * 시간복잡도
     * - O(N)
     */
    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

            for(int i = 0; i < nums.length; i++) {
                queue.offer(nums[i]);
            }

            int answer = 0;
            for(int i = 0; i < k; i ++) {
                answer = queue.poll();
            }

            return answer;
        }
    }
}
