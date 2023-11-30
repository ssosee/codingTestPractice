package toss_assistant_algorithm_study.season5.week2;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(solution.longestConsecutive(new int[]{1,2,0,1}));
    }

    /**
     * 아이디어
     * - 주어진 배열을 오름차순 정렬한다.
     * - 이전 값과 현재 값을 비교하여 연속된 숫자인지 확인한다.
     * - 만약 이전 값과 현재 값이 같으면 다음 인덱스를 탐색한다.
     * - 만약 이전 값 + 1이 현재 값과 같으면 연속된 숫자라고 판단한다.
     * - 그외는 연속된 숫자가 끊겼다고 판단하고, 연속된 숫자의 갯수를 maxCount에 저장한다.
     *
     * 자료구조
     * - 배열, 정렬
     *
     * 시간복잡도
     * - O(N)
     */
    static class Solution {
        public int longestConsecutive(int[] nums) {

            if(nums.length == 0) {
                return 0;
            }

            // 오름차순 정렬
            Arrays.sort(nums);

            int prev = nums[0];
            int count = 1;
            int maxCount = 1;
            for(int i = 0; i < nums.length; i++) {
                int current = nums[i];
                // 이전 값과 다음 값이 같으면?
                if(prev == current) {
                    continue;
                }
                // 연속된 숫자
                else if(prev + 1 == current) {
                    count++;
                }
                // 연속된 숫자가 끊기면
                else {
                    maxCount = Math.max(count, maxCount);
                    count = 1;
                }
                // 이전 값으로 만든다.
                prev = current;
            }

            // 가장 큰 연속된 숫자 갯수를 반환
            return Math.max(maxCount, count);
        }
    }
}
