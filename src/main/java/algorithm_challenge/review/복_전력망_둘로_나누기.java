package algorithm_challenge.review;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 복_전력망_둘로_나누기 {

    static List<Integer>[] nodes;

    static class Solution {
        public int solution(int n, int[][] wires) {
            /**
             * 1. 연결리스트를 생성
             * 2. 전력망을 끊었을 때 경로 탐색
             * 3. 2를 2번 진행하고 차이를 구한다.
             * 4. 3의 결과가 최소인 것을 반환
             */

            nodes = new ArrayList[n+1];
            for(int i = 1; i < n + 1; i++) {
                nodes[i] = new ArrayList<>();
            }

            for(int i = 0; i < wires.length; i++) {
                int node1 = wires[i][0];
                int node2 = wires[i][1];

                // 양방향
                nodes[node1].add(node2);
                nodes[node2].add(node1);
            }

            int min = Integer.MAX_VALUE;
            for(int i = 0; i < wires.length; i++) {
                int node1 = wires[i][0];
                int node2 = wires[i][1];

                int path1 = dfs(n, node1, node2);
                int path2 = dfs(n, node2, node1);

                min = Math.min(min, Math.abs(path1 - path2));
            }

            return min;
        }

        private int dfs(int n, int start, int compareNode) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            boolean[] visited = new boolean[n+1];
            visited[start] = true;

            int path = 0;
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();

                for(Integer nextNode : nodes[poll]) {
                    if(!visited[nextNode] && nextNode != compareNode) {
                        visited[nextNode] = true;
                        queue.offer(nextNode);
                        path++;
                    }
                }
            }

            return path;
        }
    }
}
