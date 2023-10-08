package toss_assistant_algorithm_study.season3.week3;

public class _productOfArrayExceptSelf {
    public static void main(String[] args) {

    }

    static class Solution {

        public int[] productExceptSelf(int[] nums) {
            /**
             * 자기자신을 제외한 배열의 모든원소를 곱한 결과를 배열에 넣는다.
             */
            int[] result = new int[nums.length];

            // 왼쪽으로 곱
            int leftProduct = 1;
            for(int i = 0; i < nums.length; i++) {
                result[i] = leftProduct;
                leftProduct *= nums[i];
            }

            // 오른쪽으로 곱
            int rightProduct = 1;
            for(int i = nums.length - 1; i >= 0; i--) {
                result[i] *= rightProduct;
                rightProduct *= nums[i];
            }

            return result;
        }
    }
}
