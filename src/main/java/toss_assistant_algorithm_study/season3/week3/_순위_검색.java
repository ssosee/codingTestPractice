package toss_assistant_algorithm_study.season3.week3;

import java.util.*;

public class _순위_검색 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"}
        );
    }

    static class Solution {

        static Map<String, List<Integer>> map;

        public int[] solution(String[] info, String[] query) {
            /**
             * 아이디어
             * - info가 반환될 수 있는 모든 질의를 key, 점수를 value로 저장
             * - 점수를 기준으로 오름차순 정렬한다.
             * - 점수를 이용하여 이진탐색을 수행
             */

            // info가 반환 될 수 있는 모든 질의를 key로 변환
            map = new HashMap<>();
            for(int i = 0; i < info.length; i++) {
                String[] split = info[i].split(" ");
                makeMap(split, "", 0);
            }

            // 오름차순 정렬
            for(String key : map.keySet()) {
                Collections.sort(map.get(key));
            }

            int[] answer = new int[query.length];
            int index = 0;
            for(int i = 0; i < query.length; i++) {
                String temp = query[i].replaceAll(" and ", "");
                String[] split = temp.split(" ");

                String key = split[0];
                int score = Integer.parseInt(split[1]);

                // 이진탐색
                answer[index++] = binarySearch(key, score);
            }

            return answer;
        }

        private int binarySearch(String key, int score) {
            if(!map.containsKey(key)) return 0;

            List<Integer> scores = map.get(key);

            int startIndex = 0;
            int endIndex = scores.size() - 1;

            while (startIndex <= endIndex) {
                int midIndex = (startIndex + endIndex) / 2;
                if(scores.get(midIndex) < score) startIndex = midIndex + 1;
                else endIndex = midIndex - 1;
            }

            return scores.size() - startIndex;
        }

        private void makeMap(String[] split, String key, int depth) {

            if(depth == 4) {
                if(!map.containsKey(key)) {
                    List<Integer> scores = new ArrayList<>();
                    map.put(key, scores);
                }
                map.get(key).add(Integer.parseInt(split[depth]));
                return;
            }

            makeMap(split, key+"-", depth+1);
            makeMap(split, key+split[depth], depth+1);
        }
    }
}
