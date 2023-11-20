package toss_assistant_algorithm_study.season5.week1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RotateArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }

    /**
     * 아이디어
     * - 큐와 스택을 이용한다.
     * [1,2,3,4,5,6,7]
     *
     * 1. 큐에 넣는다.
     *  - [7,6,5,4,3,2,1]
     *
     * 2. k만큼 큐에서 빼고 넣는다.
     *  - [6,5,4,3,2,1,7]
     *  - [5,4,3,2,1,7,6]
     *  - [4,3,2,1,7,6,5]
     *
     * 3. 스택에 옮긴다.
     *  - [4,3,2,1,7,6,5]
     *
     * 4. 스택에 넣은 것을 배열에 옮긴다.
     *  - [5,6,7,1,2,3,4]
     *
     * 시간복잡도
     * - O(N)
     *
     * 자료구조
     * - 큐, 스택
     *
     */
    static class Solution {
        public void rotate(int[] nums, int k) {
            Queue<Integer> queue = new ArrayDeque<>();
            for(int i = nums.length - 1; i >= 0; i--) {
                queue.offer(nums[i]);
            }

            for(int i = 0; i < k; i++) {
                Integer poll = queue.poll();
                queue.offer(poll);
            }

            Stack<Integer> stack = new Stack<>();
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                stack.push(poll);
            }

            int index = 0;
            while (!stack.isEmpty()) {
                nums[index++] = stack.pop();
            }
        }
    }
}
