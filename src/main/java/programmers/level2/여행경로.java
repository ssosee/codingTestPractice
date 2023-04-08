package programmers.level2;

import java.util.*;

public class 여행경로 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[][]{
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        })));
    }
    static class Solution {
        public String[] solution(String[][] tickets) {
            Queue<String> pq = new PriorityQueue<>();
            boolean[] visited = new boolean[tickets.length];
            dfs(pq, tickets, visited, 0, "ICN", "ICN");

            String[] split = pq.peek().split("->");

            return split;
        }

        private void dfs(Queue<String> pq, String[][] tickets, boolean[] visited, int depth, String start, String path) {

            // 탐색을 원하는 깊이에 도달하면
            if(tickets.length == depth) {
                // 지금까지 탐색한 경로를 저장
                pq.offer(path);
                return;
            }

            for(int i = 0; i < tickets.length; i++) {
                // 방문 이력이 없고, 출발 도시(start)가 여행 경로의 도착지(tickets[i][0])인 경우
                if(!visited[i] && start.equals(tickets[i][0])) {
                    // 방문
                    visited[i] = true;
                    // dfs 수행
                    dfs(pq, tickets, visited, depth + 1, tickets[i][1], path + "->" + tickets[i][1]);
                    // dfs를 수행했으면 방문 이력 초기화
                    visited[i] = false;
                }
            }
        }
    }
}
