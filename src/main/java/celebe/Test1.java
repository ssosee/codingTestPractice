package celebe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{10, 1, 10, 1, 1, 4, 3, 10}, 6));
        //System.out.println(solution.solution(new int[]{5, 1, 9, 8, 10, 5}, 3));
    }

    static class Solution {
        public int solution(int[] estimates, int k) {

            /**
             * k일 동안 추정 접속자 수의 합이 최대가 되도록 이벤트 기간을 정하려고함
             * k일 동안 추정 접속자 수의 합이 최대일 때 추정 접속자 수 합을 반환
             */
            int sum = 0;

            // sum 초기값
            for(int i = 0; i < k; i++) {
                sum += estimates[i];
            }

            //int p1 = 0;
            int p2 = k;
            int maxSum = sum;
            for(int p1 = 0; p2 < estimates.length; p1++) {
                sum = sum - estimates[p1] + estimates[p2];
                maxSum = Math.max(maxSum, sum);
                p2++;
            }

            return maxSum;
        }
    }
}
