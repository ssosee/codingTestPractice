package toss_assistant_algorithm_study.seaaon4.week3;

public class HouseRobber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{1, 2, 3, 1}));
        System.out.println(solution.rob(new int[]{2,7,9,3,1}));
        System.out.println(solution.rob(new int[]{2,1,1,2}));
    }

    /**
     * 아이디어
     * - 다이나믹프로그래밍
     * - 서로 인접한 집은 털 수 없음
     * - i번째 집 털었을 때 얻는 이득  = num[i] + dp[i-2]
     * - i번째 집 안 털었을 때 얻는 이득 = dp[i-1]
     *  dp[i] = Max(dp[i-1], num[i] + dp[i-2])
     *
     * 자료구조
     * - 1차원 배열
     *
     * 시간복잡도
     * - O(N)
     *
     */
    static class Solution {
        public int rob(int[] nums) {

            if(nums.length == 1) return nums[0];

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(dp[0], nums[1]);
            for(int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
            }

            return dp[nums.length - 1];
        }
    }
}
