package inflearn.arrayalgorithm.slidingwindow;

/**
 * 최대 매출
 */
import java.util.*;

public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalDays = sc.nextInt();
        int days = sc.nextInt();
        int[] profit = new int[totalDays];
        for(int i = 0; i < totalDays; i++) {
            profit[i] = sc.nextInt();
        }
        System.out.println(new Solution().slidingWindowAlgorithm(days, profit));
    }
    static class Solution {
        /**
         * sliding window algorithm
         */
        public int slidingWindowAlgorithm(int days, int[] profit) {
            int answer = 0;
            int sum = 0;
            for(int i = 0; i < days; i++) {
                sum += profit[i];
            }
            answer = sum;
            for(int i = days; i < profit.length; i++) {
                sum += profit[i] - profit[i-days];
                answer = Math.max(sum, answer);
            }

            return answer;
        }
    }
}
