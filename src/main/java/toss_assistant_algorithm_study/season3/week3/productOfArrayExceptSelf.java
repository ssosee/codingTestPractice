package toss_assistant_algorithm_study.season3.week3;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class productOfArrayExceptSelf {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.productExceptSelf(new int[]{-1,1,0,-3,3}));
        //System.out.println(solution.productExceptSelf(new int[]{0, 0}));
    }

    /**
     * 아이디어
     * - 나눗셈을 사용하지 않고, O(N)으로 해결
     * - 자기자신을 제외한 모든 원소의 곱을 구해야 한다.
     * - 왼쪽, 오른쪽으로 나누어서 곱셈을 진행
     *
     * nums = [a, b, c, d]
     * 왼쪽에서 오른쪽으로 곱셈연산
     * i = 0 -> result = [1, ]                      leftProduct = 1, num[0] = a
     * i = 1 -> result = [1, 1*a, ]                 leftProduct = 1*a, num[1] = b
     * i = 2 -> result = [1, 1*a, 1*a*b, ]          leftProduct = 1*a*b, num[2] = c
     * i = 3 -> result = [1, 1*a, 1*a*b, 1*a*b*c]   leftProduct = 1*a*b*c,  num[3] = d
     *
     * 오른쪽에서 왼쪽으로 곱셈연산
     * i = 3 -> result = [1, 1*a, 1*a*b, 1*a*b*c *1]                        rightProduct = 1, num[3] = d
     * i = 2 -> result = [1, 1*a, 1*a*b *1*d, 1*a*b*c *1]                   rightProduct = 1*d, num[2] = c
     * i = 1 -> result = [1, 1*a *1*d*c, 1*a*b *1*d, 1*a*b*c *1]            rightProduct = 1*d*c, num[1] = b
     * i = 0 -> result = [1 *1*d*c*b, 1*a *1*d*c, 1*a*b *1*d, 1*a*b*c *1]   rightProduct = 1*d*c*b, num[0] = a
     *
     * 자기자신을 제외한 곱배열 생성
     * result   =   [1 *1*d*c*b, 1*a *1*d*c, 1*a*b *1*d, 1*a*b*c *1]
     * nums     =   [a,          b,          c,          d]
     *
     * 시간복잡도
     * - O(N)
     *
     * 자료구조
     * - 배열
     */
    static class Solution {
        public int[] productExceptSelf(int[] nums) {

            // 자기자신을 제외한 모든 원소의 곱
            int n = nums.length;
            int[] result = new int[n];

            // 왼쪽에서 오른쪽으로 곱셈 결과를 계산합니다.
            int leftProduct = 1;
            for (int i = 0; i < n; i++) {
                result[i] = leftProduct;
                leftProduct *= nums[i];
            }

            // 오른쪽에서 왼쪽으로 곱셈 결과를 계산하면서 결과를 업데이트합니다.
            int rightProduct = 1;
            for (int i = n - 1; i >= 0; i--) {
                result[i] *= rightProduct;
                rightProduct *= nums[i];
            }

            return result;
        }
    }
}
