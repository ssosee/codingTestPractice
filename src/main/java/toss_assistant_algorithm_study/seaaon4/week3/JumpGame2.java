package toss_assistant_algorithm_study.seaaon4.week3;

public class JumpGame2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(solution.jump(new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3}));
        //System.out.println(solution.jump(new int[]{1,2,1,1,1}));
    }

    static class Solution {
        /**
         * 아이디어
         * - 현재 지점에서 최대로 점프할 수 있는 거리(i + nums[i])
         * - 현재 위치에서 최대 거리(currentMax)가 지금까지 최대 거리(maxIndex)에 도달하면 카운트를 증가한다.
         *  - 이때 지금까지 최대거리를 갱신한다.
         *
         * 자료구조
         * - 배열
         *
         * 시간복잡도
         * - O(N)
         */
        public int jump(int[] nums) {
            int count = 0;
            int maxIndex = 0;
            int currentMax = 0;

            for(int i = 0; i < nums.length; i++) {
                currentMax = Math.max(currentMax, i + nums[i]);
                if(i == maxIndex) {
                    count++;
                    maxIndex = currentMax;
                }
            }

            return count;
        }
    }
}
