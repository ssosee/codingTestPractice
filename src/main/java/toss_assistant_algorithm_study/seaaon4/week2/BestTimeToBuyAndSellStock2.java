package toss_assistant_algorithm_study.seaaon4.week2;

public class BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.maxProfit(new int[]{7,1,5,3,6,4}));
        //System.out.println(solution.maxProfit(new int[]{7,6,4,3,1}));
        System.out.println(solution.maxProfit(new int[]{1,2,3,4,5}));
    }

    static class Solution {
        /**
         * 아이디어
         * - 1주를 매수, 매도를 반복 했을 때 최대 이득 계산
         * - 요소간 차이를 구한다.
         * - 현재 가격이 어제 가격보다 크면 매도를한다.
         *
         * 시간복잡도
         * - O(N)
         *
         * 자료구조
         * - 배열
         *
         */
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int pastPrice = prices[0];
            for(int i = 0; i < prices.length; i++) {
                // 현재 가격
                int price = prices[i];
                // 현재가격이 어제 가격보다 크면
                if(pastPrice < price) {
                    // 매도
                    int profit = price - pastPrice;
                    maxProfit += profit;
                }
                // 어제 가격 저장
                pastPrice = prices[i];
            }

            return maxProfit;

        }
    }
}
