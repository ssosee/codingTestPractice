package toss_assistant_algorithm_study.seaaon4.week2;

public class JumpGame {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.canJump(new int[]{2,3,1,1,4}));
        System.out.println(solution.canJump(new int[]{3,2,1,0,4}));
        //System.out.println(solution.canJump(new int[]{2,0}));
        //System.out.println(solution.canJump(new int[]{1,3,2}));
        //System.out.println(solution.canJump(new int[]{8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5}));
    }

    /**
     * 아이디어
     * - (현재 위치 + 최대 점프)의 최댓값이 배열의 길이보다 크거나 같으면 true 반환
     *
     * 자료구조
     * - 배열
     *
     * 시간복잡도
     * - O(N)
     *
     */
    static class Solution {

        public boolean canJump(int[] nums) {
            int distance = 0;
            // i는 최대로 distance까지만 움직일 수 있다.
            for(int i = 0; i <= nums.length; i++) {
                int currentMaxJump = i + nums[i];
                distance = Math.max(distance, currentMaxJump);

                // 최대 길이(distance)가 배열의 크기보다 크거나 같으면
                if(distance >= nums.length - 1) {
                    return true;
                }
            }

            return false;
        }



        boolean result = false;

        public boolean canJumpFail(int[] nums) {
            int start = 0;

            boolean[] visited = new boolean[nums.length];
            visited[0] = true;
            dfs(nums, start, visited);

            return result;
        }



        private void dfs(int[] nums, int start, boolean[] visited) {
            if(start == nums.length - 1) {
                result = true;
                return;
            }

            if(start > nums.length - 1) return;

            int jump = nums[start];
            if(jump == 0) return;

            for (int i = 1; i <= jump; i++) {
                if(start + i > nums.length - 1) break;
                else if(!visited[start + i]) {
                    visited[start + i] = true;
                    dfs(nums, start + i, visited);
                    visited[start + i] = false;
                }
            }
        }
    }
}
