package toss_assistant_algorithm_study.seaaon4.week2;

public class _JumpGame {
    public static void main(String[] args) {

    }

    static class Solution {
        public boolean canJump(int[] nums) {
            int maxDistance = 0;
            for(int i = 0; i <= maxDistance; i++) {
                // 현재 위치에서 점프해서 갈 수 있는 거리
                int jumpDistance = i + nums[i];
                maxDistance = Math.max(maxDistance, jumpDistance);
                if(maxDistance >= nums.length - 1) return true;
            }
            return false;
        }
    }
}
