package toss_assistant_algorithm_study.season3.week3;

public class _IncreasingTripletSubsequence {
    static class Solution {
        public boolean increasingTriplet(int[] nums) {
            /**
             * i < j < k && nums[i] < nums[j] < nums[k] -> true
             *
             *
             * nums = [2,1,5,0,4,6]
             * first >= 2(num[0])  ->  true       first = 2
             *
             * first >= 1(num[1])  ->  true       first = 1
             *
             * first >= 5(num[2])  ->  false      first = 1
             * second >= 5(num[2]) ->  true       first = 1, second = 5
             *
             * first >= 0(num[3])  ->  true       first = 0
             *
             * first >= 4(num[4])  ->  false      first = 0, second = 5
             * second >= 4(num[4]) ->  true       first = 1, second = 4
             *
             * first >= 6(num[5])  ->  false      first = 0, second = 4
             * second >= 6(num[5]) ->  false      first = 0, second = 4
             * -> true
             */

            int first = Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;
            for(int i = 0; i < nums.length; i++) {
                if(first >= nums[i]) first = nums[i];
                else if(second >= nums[i]) second = nums[i];
                else return true;
            }

            return false;
        }
    }
}
