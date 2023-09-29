package toss_assistant_algorithm_study.season3;

import java.util.*;
import java.util.stream.Collectors;

public class 시소_짝꿍 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{100, 180, 360, 100, 270}));
    }

    /**
     * 아이디어
     * - 탑승한 사람의 무게와 시소 측과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소짝꿍
     * [100, 100] (2, 2), (3, 3), (4, 4)
     * [180 * 4, 360 * 2] (4, 2)
     * [180 * 3, 270 * 2] (3, 2)
     * [270 * 4, 360 * 3] (4, 3)
     *
     * 가능한 비율 1:1, 2:3, 3:4, 2:4
     *
     * 시간복잡도
     * - O(N)
     *
     * 자료구조
     * - HashMap
     */
    static class Solution {
        public long solution(int[] weights) {
            long count = 0;
            // 오름차순 정렬
            Arrays.sort(weights);
            Map<Float, Integer> map = new HashMap<>();

            for(int w : weights) {
                // 짝꿍 계산(오름차순 정렬했기 때문에 1번만 계산하면 된다.)
                float w1 = w * 1.0f;
                float w2 = (w * 2.0f) / 3.0f;
                float w3 = (w * 3.0f) / 4.0f;
                float w4 = (w * 2.0f) / 4.0f;

                if(map.containsKey(w1)) count += map.get(w1);
                if(map.containsKey(w2)) count += map.get(w2);
                if(map.containsKey(w3)) count += map.get(w3);
                if(map.containsKey(w4)) count += map.get(w4);

                map.put(w * 1.0f, map.getOrDefault(w * 1.0f, 0) + 1);
            }

            return count;
        }
    }
}
