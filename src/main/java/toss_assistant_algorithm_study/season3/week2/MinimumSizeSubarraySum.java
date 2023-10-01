package toss_assistant_algorithm_study.season3.week2;

import java.util.Arrays;

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        // System.out.println(solution.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
        // System.out.println(solution.minSubArrayLen(4, new int[]{1,4,4}));
    }

    /**
     * 아이디어
     *  - 투포인터, 중복합(누적합)
     *  [1, 2, 2, 3, 3, 4], target = 7
     *  1 < 7
     *  1+2 < 7
     *  1+2+2 < 7
     *  1+2+2+3 > 7
     *  2+2+3 = 7
     *  2+3 < 7
     *  2+3+3 > 7
     *  3+3 < 7
     *  3+3+4 > 7
     *  3+4 = 7
     *
     * 자료구조
     *  - 배열
     *
     * 시간복잡도
     *  - O(N)
     */
    static class Solution {
        public int minSubArrayLen(int target, int[] nums) {

            int sum = 0;
            int head = 0;
            int tail = 0;
            int min = Integer.MAX_VALUE;

            while (tail < nums.length) {
                // sum이 target 보다 작은 경우
                sum += nums[tail++];
                // sum이 target 보다 크거나 같은 경우
                while (sum >= target) {
                    sum -= nums[head++];
                    int length = tail - head + 1;
                    min = Math.min(min, length);
                }
            }

            // subArray가 없으면
            if(min == Integer.MAX_VALUE) return 0;

            return min;
        }
    }
}
