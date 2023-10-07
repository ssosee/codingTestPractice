package toss_assistant_algorithm_study.season3.week3;

import java.util.*;

public class 순위_검색 {
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
        /**
         * 아이디어
         * - info가 포함될 수 있는 모든 질의를 key로 만든다.
         * - value는 key의 점수
         * - query를 key로 넣고 value를 가져와 이진 탐색을 시작한다.(효율성)
         *
         * e.g)
         * info
         * java backend junior pizza 150을 찾기위해서는 다음과 같은 query 필요
         *
         * query
         * 1. - - - -
         * 2. java - - -
         * 3. java backend - -
         * 4. java backend junior -
         * 5. java backend - pizza
         * 6. java backend junior pizza
         * 7. java - junior -
         * 8. java - junior pizza
         * 9. java - - pizza
         * 10. - backend - -
         * 11. - backend junior -
         * 12. - backend junior pizza
         * 13. - backend - pizza
         * 14. - - junior -
         * 15. - - junior pizza
         * 16. - - - pizza
         *
         * 자료구조
         * - List를 vaule로 하는 HashMap (MultiValueMap과 다름)
         *
         * 시간복잡도
         * - O(N)
         */
        static Map<String, List<Integer>> map;
        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            map = new HashMap<>();
            // info를 찾을 수 있는 query를 만들고 query를 key로 value는 점수로 저장한다.
            for(int i = 0; i < info.length; i++) {
                String[] split = info[i].split(" ");
                createInfoMap(split, "", 0);
            }

            // value를 오름차순 정렬
            for(String key : map.keySet()) {
                Collections.sort(map.get(key));
            }

            int index = 0;
            for(int i = 0; i < query.length; i++) {
                String tempQuery = query[i].replaceAll(" and ", "");
                String[] tempQuerySplit = tempQuery.split(" ");

                String resultQuery = tempQuerySplit[0];
                int score = Integer.parseInt(tempQuerySplit[1]);

                int count = binarySearch(resultQuery, score);
                answer[index++] = count;
            }

            return answer;
        }

        private int binarySearch(String resultQuery, int score) {
            // map에 존재하지 않으면 0
            if(!map.containsKey(resultQuery)) return 0;

            List<Integer> scores = map.get(resultQuery);

            int start = 0;
            int end = scores.size() - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if(score > scores.get(mid)) start = mid + 1;
                else end = mid - 1;
            }

            return scores.size() - start;
        }

        private void createInfoMap(String[] infoSplit, String key, int depth) {
            if(depth == 4) {
                if(!map.containsKey(key)) {
                    List<Integer> list = new ArrayList<>();
                    map.put(key, list);
                }
                map.get(key).add(Integer.parseInt(infoSplit[depth]));
                return;
            }
            createInfoMap(infoSplit, key+"-", depth+1);
            createInfoMap(infoSplit, key+infoSplit[depth], depth+1);
        }
    }
}
