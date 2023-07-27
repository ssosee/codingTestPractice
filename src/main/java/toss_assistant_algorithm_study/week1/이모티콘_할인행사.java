package toss_assistant_algorithm_study.week1;

import java.util.Arrays;

public class 이모티콘_할인행사 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});

        Arrays.stream(result)
                .forEach(System.out::println);
    }

    static class Solution {
        static int[] discountRate = {10, 20, 30, 40};
        static int[] emoticonsDiscountRate; // 이모티콘별 할인율
        static int finalPlusServiceCount = 0;
        static int finalTotalCost = 0;
        public int[] solution(int[][] users, int[] emoticons) {
            /**
             * 1. 서비스 가입자 최대로(1)
             * 2. 매출액 최대로(2)
             *
             * *이모티콘마다 할인율이 다름(10, 20, 30, 40%)
             *
             * 1. 일정 비율(%) 이상 할인하는 모든 임티 구매
             * 2. 임티 구매 비용(₩)의 합이 일정 가격 이상이면, 임티 구매를 모두 취소하고 임티 플러스 서비스 가입
             */
            emoticonsDiscountRate = new int[emoticons.length];
            dfs(0, users, emoticons);
            return new int[]{finalPlusServiceCount, finalTotalCost};
        }

        public static void dfs(int depth, int[][] users, int[] emoticons) {

            if(depth == emoticons.length) {
                int tempJoinServiceCount = 0;
                int usersCost = 0; // 모든 회원의 구매 금액

                for (int i = 0; i < users.length; i++) {
                    int userRate = users[i][0];
                    int userCost = users[i][1];
                    int currentUserCost = 0; // 현재 회원의 구매 금액

                    for(int j = 0; j < emoticons.length; j++) {
                        // 일정 비율 이상 할인하면
                        if(userRate <= emoticonsDiscountRate[j]) {
                            // 모두 구매
                            currentUserCost += emoticons[j] - emoticons[j] * emoticonsDiscountRate[j] / 100;
                        }
                        // 구매비용이 일정가격 이상이면
                        if (userCost <= currentUserCost) {
                            // 구매 취소
                            currentUserCost = 0;
                            // 서비스 가입
                            tempJoinServiceCount++;
                            break;
                        }
                    }

                    // 금액 갱신
                    usersCost += currentUserCost;
                }

                // 현재 서비스 가입자가 더 많으면
                if(tempJoinServiceCount > finalPlusServiceCount) {
                    finalPlusServiceCount = tempJoinServiceCount;
                    finalTotalCost = usersCost;
                }
                // 서비스 가입자 수가 같으면
                else if(tempJoinServiceCount == finalPlusServiceCount){
                    // 최고 금액으로 갱신
                    finalTotalCost = Math.max(usersCost, finalTotalCost);
                }

                return;
            }

            for(int i = 0; i < discountRate.length; i++) {
                emoticonsDiscountRate[depth] = discountRate[i];
                dfs(depth + 1, users, emoticons);
            }
        }
    }
}
