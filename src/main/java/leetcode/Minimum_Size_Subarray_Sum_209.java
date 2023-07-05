package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Minimum_Size_Subarray_Sum_209 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(result);
    }

    static class Solution {
        // 투포인터 사용
        public int minSubArrayLen(int target, int[] nums) {

            int result = Integer.MAX_VALUE;
            int sum = 0;
            int start = 0;
            int end = 0;
            while (end < nums.length) {
                // 끝 인덱스를 더하면서 합을 구한다.
                sum += nums[end++];
                // 합이 target보다 크거나 같으면
                while (sum >= target) {
                    // 합에서 시작 인덱스의 값을 빼준다.
                    sum -= nums[start++];
                    // 배열의 최소 길이 계산
                    result = Math.min(result, end - start + 1);
                }
            }

            if(result == Integer.MAX_VALUE) return 0;

            return result;
        }
    }
}
