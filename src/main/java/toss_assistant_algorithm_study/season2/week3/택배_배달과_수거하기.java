package toss_assistant_algorithm_study.season2.week3;

import java.util.PriorityQueue;
import java.util.Queue;

public class 택배_배달과_수거하기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
    }

    static class Solution {

        public long solution(int cap, int n, int[] deliveries, int[] pickupCounts) {

            /**
             * 아이디어
             *  배달, 수거 -> 최소거리
             *  최단 거리는 결국 가장 멀리있는 배달 or 픽업을 먼저 해서 이동거리를 최소한으로 줄이면 된다.
             *
             * 자료구조
             *  배열, 중복합
             *
             * 시간복잡도
             *  O(N)
             */

            int totalDeliveryCount = 0;
            int totalPickupCount = 0;
            long distance = 0;

            for(int i = n-1; i >= 0; i--) {
                // 배달 수량
                totalDeliveryCount += deliveries[i];
                // 수거 수량
                totalPickupCount += pickupCounts[i];

                // 배달 수량 또는 수거 수량이 음수이면
                // 돌아오는 길에 배달 또는 수거할 수 있다는 의미!
                while (totalDeliveryCount > 0 || totalPickupCount > 0) {
                    totalDeliveryCount -= cap;
                    totalPickupCount -= cap;
                    distance += (long) (i+1) * 2L;
                }
            }

            return distance;
        }
    }
}
