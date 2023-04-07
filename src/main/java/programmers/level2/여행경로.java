package programmers.level2;

import java.util.*;

public class 여행경로 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[][]{
                {"ICN", "JFK"},
                {"ICN", "AAD"},
                {"JFK", "ICN"}
        })));
    }
    static class Solution {
        public String[] solution(String[][] tickets) {
            // 항상 ICN에서 출발
            int x = tickets.length;
            int y = tickets[0].length;

            List<String> bfs = bfs(tickets, x);
            String[] answer = bfs.toArray(new String[bfs.size()]);

            int code = 0;
            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    if (!map.containsKey(tickets[i][j])) {
                        map.put(tickets[i][j], code++);
                    }
                }
            }

            System.out.println(1);

            return answer;
        }

        private List<String> bfs(String[][] tickets, int x) {
            // 알파벳 순으로 오름차순 정렬
            Queue<String> pq = new PriorityQueue<>();
            // 간선 방문 배열
            boolean[] visited = new boolean[x];
            List<String> answer = new ArrayList<>();

            pq.offer("ICN");
            answer.add("ICN");

            while (!pq.isEmpty()) {
                String poll = pq.poll();
                System.out.println(poll);
                for (int i = 0; i < x; i++) {
                    if (!visited[i] && tickets[i][0].equals(poll)) {
                        visited[i] = true;
                        pq.offer(tickets[i][1]);
                    }
                }
            }

            return answer;
        }
    }
}
