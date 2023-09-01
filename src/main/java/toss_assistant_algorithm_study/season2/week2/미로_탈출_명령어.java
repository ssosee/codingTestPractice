package toss_assistant_algorithm_study.season2.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 미로_탈출_명령어 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(3, 4, 2, 3, 3, 1, 5));
    }

    static class Solution {
        static int[] moveX = {1, -1, 0, 0};
        static int[] moveY = {0, 0, 1, -1};
        static String[] move = {"d", "u", "r", "l"};
        public String solution(int n, int m, int x, int y, int r, int c, int k) {

            /**
             * 아이디어
             *  bfs를 활용한 완전탐색
             *
             *  k - 최단거리 -> 홀수 이면 impossible
             *  최단거리가 4이고, k가 5이면,
             *  최단거리만큼 이동한 후, 다른곳으로 이동후 다시 목적지로 이동해야함.
             *  위 행위가 짝수의 이동경로를 가지기 때문에
             *  k - 최단거리가 짝수여야 성립
             *
             * 자료구조
             *  우선순위 큐, 3차원 배열
             *
             * 시간복잡도
             *  O(N^2)
             */
            return bfs(n, m, x, y, r, c, k);
        }

        private String bfs(int n, int m, int x, int y, int r, int c, int k) {
            Queue<Path> pq = new PriorityQueue<>((o1, o2) -> o1.paths.compareTo(o2.paths));
            boolean[][][] visited = new boolean[n+1][m+1][k+1];
            visited[x][y][0] = true;
            pq.offer(new Path(x, y, 0, new StringBuilder()));

            /**
             * k - 최단거리 -> 홀수 이면 impossible
             * 최단거리가 4이고, k가 5이면,
             * 최단거리만큼 이동한 후, 다른곳으로 이동후 다시 목적지로 이동해야함.
             * 위 행위가 짝수의 이동경로를 가지기 때문에
             * k - 최단거리가 짝수여야 성립
             */
            int shortestDistance = Math.abs(x - r) + Math.abs(y - c);
            if((k - shortestDistance) % 2 == 1) return "impossible";

            while (!pq.isEmpty()) {
                Path poll = pq.poll();

                // 목적지에 도착
                if(poll.x == r && poll.y == c && poll.depth == k) {
                    return poll.paths.toString();
                }

                for(int i = 0; i < 4; i++) {
                    int newX = poll.x + moveX[i];
                    int newY = poll.y + moveY[i];

                    // 범위 체크
                    if(newX < 1 || newY < 1 || newX >= n+1 || newY >= m+1) continue;

                    if(poll.depth < k && !visited[newX][newY][poll.depth + 1]) {
                        visited[newX][newY][poll.depth] = true;
                        pq.offer(new Path(newX, newY, poll.depth + 1, new StringBuilder(poll.paths).append(move[i])));
                    }
                }
            }

            return "impossible";

        }

        static class Path {
            private int x;
            private int y;
            private int depth;
            private StringBuilder paths;

            public Path(int x, int y, int depth, StringBuilder paths) {
                this.x = x;
                this.y = y;
                this.depth = depth;
                this.paths = paths;
            }
        }

    }
}
