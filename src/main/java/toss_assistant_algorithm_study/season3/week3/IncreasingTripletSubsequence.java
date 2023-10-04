package toss_assistant_algorithm_study.season3.week3;

import java.util.List;

public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.increasingTriplet(new int[]{20,100,10,12,5,13}));
        //System.out.println(solution.increasingTriplet(new int[]{1,5,0,4,1,3}));
        //System.out.println(solution.increasingTriplet(new int[]{6, 7, 1, 2}));
        System.out.println(solution.increasingTriplet(new int[]{5,1,5,5,2,5,4}));
    }

    static class Solution {
        public boolean increasingTriplet(int[] nums) {
            int first = Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] <= first) first = nums[i];
                else if(nums[i] <= second) second = nums[i];
                else return true;
            }
            return false;
        }

        public boolean increasingTripletFail(int[] nums) {
            int i = 0;
            int j = 1;
            int k = 2;

            if(nums.length < k) return false;

            while (k < nums.length) {
                int numI = nums[i++];
                int numJ = nums[j++];
                int numK = nums[k++];

                if(numI < numJ && numJ < numK) {
                    return true;
                }
                else if(numI < numJ && numJ > numK) {
                    int tempK = k;

                    while (k < nums.length) {
                        numK = nums[k++];
                        if(numJ < numK) return true;
                    }

                    k = tempK;
                    int tempJ = j;
                    while (k < nums.length) {
                        numK = nums[k++];
                        while (j < k) {
                            numJ = nums[j++];
                            if(numI < numJ && numJ < numK) return true;
                        }
                    }

                    j = tempJ;
                    k = tempK;
                }
            }

            return false;
        }
    }
}
