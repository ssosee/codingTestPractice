package toss_assistant_algorithm_study.season7;

import java.util.Arrays;

public class TwoSum2InputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.badTwoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    /**
     * 아이디어
     * - 이중 loop를 사용하여 탐색한다.
     *
     * 자료구조
     * - 배열
     *
     * 시간복잡도
     * - O(N^2)
     *
     * 결과
     * - 400ms
     */

    static class Solution {
        public int[] badTwoSum(int[] numbers, int target) {
            for(int i = 0; i < numbers.length; i++) {
                for(int j = i + 1; j < numbers.length; j++) {
                    int sum = numbers[i] + numbers[j];
                    if(sum == target) {
                        return new int[]{i+1, j+1};
                    }
                    else if(sum > target) {
                        break;
                    }
                }
            }

            return new int[]{};
        }

        /**
         * 아이디어
         * - 투포인터를 사용한다.
         * - 배열은 오름차순으로 정렬되어 있다.
         * - 최좌측(left)과 최우측(right)을 인덱스를 잡고 sum이 target보다 작으면 left++
         * - 최좌측(left)과 최우측(right)을 인덱스를 잡고 sum이 target보다 크면 right--
         *
         *  numbers = [2,7,11,15,17], target = 18
         *  left: 0, right: 4
         *      1. numbers[0] + numbers[4] = 19 -> target 보다 크다 -> right--
         *      2. numbers[0] + numbers[3] = 17 -> target 보다 작다 -> left++
         *      3. numbers[1] + numbers[3] = 22 -> target 보다 크다 -> right--
         *      4. numbers[1] + numbers[2] = 18 -> target과 일치!
         *
         * 자료구조
         * - 1차원 배열
         *
         * 시간복잡도
         * - O(N)
         *
         * 결과
         * - 2ms
         */
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if(sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    break;
                }
            }

            return new int[]{left+1, right+1};
        }
    }
}
