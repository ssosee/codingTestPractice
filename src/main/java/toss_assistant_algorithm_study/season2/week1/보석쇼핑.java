package toss_assistant_algorithm_study.season2.week1;

import java.util.*;
import java.util.stream.Collectors;

public class 보석쇼핑 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }

    static class Solution {
        public int[] solution(String[] gems) {

            /**
             * 특정 범위의 보석을 모두 구매하되,
             * 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간
             *
             * 1. 아이디어
             *  - 보석 종류를 알아낸다.
             *  - 보석, 보석갯수를 Map으로 표현
             *  - 2 point 방식을 이용
             *  - start번째의 보석이 중복이면 start번째의 보석 1개를 삭제하고, start를 증가
             *  - 모든 종류의 보석을 갖고 있고, 최단거리이면 최단거리를 갱신하고, 시작점, 끝점을 갱신
             *
             *  2. 시간복잡도
             *  gems의 크기가 100,000이기 때문에 O(N)으로 해결해야 한다.
             *  (N이 100,000,000일때 약 1초)
             *
             *  3. 자료구조
             *  Map, Set
             */

            Set<String> gemsSet = Arrays.stream(gems)
                    .collect(Collectors.toSet());
            Map<String, Integer> gemMap = new HashMap<>();

            int start = 0; // 시작 지점
            int minLength = Integer.MAX_VALUE;

            int finalStart = 0;
            int finalEnd = 0;
            for (int end = 0; end < gems.length; end++) {
                // 보석 쇼핑백에 보석을 넣는다.
                gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);

                while (gemMap.get(gems[start]) > 1) {
                    // start번째의 보석이 중복 이라면, 보석의 갯수를 줄이고 시작구간(start)을 1 증가
                    gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                    start++;
                }

                // 모든 보석종류를 갖고 있고, 최단길이가 (끝점 - 시작점)보다 크면
                if (gemMap.size() == gemsSet.size() && minLength > (end - start)) {
                    minLength = end - start;
                    finalStart = start + 1;
                    finalEnd = end + 1;
                }
            }

            return new int[]{finalStart, finalEnd};
        }
    }
}
