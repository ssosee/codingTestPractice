package celebe;

import java.util.*;

public class Test2 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1, 1, 2, 3, 3, 3}, new int[]{1, 1, 2, 3, 3, 4}));
    }

    static class Solution {
        public int solution(int[] cards1, int[] cards2) {

            /**
             * 같은 장수의 카드 뭉치
             * 같은 카드끼리 뭉칠 경우 점수가 높다.
             * 같은 카드 뭉치 점수 = n * (n + 1) / 2 점
             *
             * 카드 뭉치를 서로 교환하여 카드들의 점수 합계 최대가 되고
             * (단, 카드 교환은 한 번에 1장 씩 | 교환 횟수 제한 없음)
             * 이때 필요한 최소 교환 횟수(tradeCount)
             */

            int tradeCount = 0;

            // 오름차순 정렬
            Arrays.sort(cards1);
            Arrays.sort(cards2);
            int score = Integer.MIN_VALUE;

            for(int i = 0; i < cards1.length; i++) {
                for(int j = 0; j < cards2.length; j++) {
                    int i1 = cards1[i];
                }
            }

            // 최소
            return tradeCount;
        }

        private static Map<Integer, Integer> putCardToMap(int[] cards) {
            Map<Integer, Integer> cardMap = new HashMap<>();
            for(int i = 0; i < cards.length; i++) {
                cardMap.put(cards[i], cardMap.getOrDefault(cards[i], 0) + 1);
            }

            return cardMap;
        }

        private int calculateScore(int[] cards) {
            int score = 0;
            for(int i = 0; i < cards.length; i++) {
                for(int j = 0; j < cards.length; j++) {

                }
            }
        }
    }
}
