package baekjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz7576 {
    static int m = 0;
    static int n = 0;
    static int[][] map = new int[n][m];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 1: 익은 토마토
         * 0: 익지 않은 토마토
         * -1: 빈칸
         *
         * 1의 위치에서 동시다발적으로 탐색해야 한다.
         * 1의 위치에서 탐색하여 0을 발견하면 0을 1로 변경한다.
         * 이때 depth를 1 증가 시킨다.
         * depth가 최대인 것을 출력한다.
         */

        // 익은 토마토를 큐에 먼저 다 넣는다.
        Queue<Node> queue = new ArrayDeque<>();
        int[][] visited = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1) {
                    queue.add(new Node(i, j));
                }
            }
        }
        bfs(visited, queue);
    }

    private static void bfs(int[][] visited, Queue<Node> queue) {
        // 큐에는 이미 익은 토마토의 위치가 들어가 있다.
        // 문제에서 익은 토마토는 1개 이상이라고 함
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int[] upDown = {1, -1, 0, 0};
            int[] leftRight = {0, 0, 1, -1};
            for(int i = 0; i < 4; i++) {
                // 익은 토마토 주변을 탐색할 x, y 좌표
                int newX = node.getX() + upDown[i];
                int newY = node.getY() + leftRight[i];

                // 범위체크
                if(newX < 0 || newY < 0 || newX >= n || newY >= m) {
                    continue;
                }

                // 새로운 위치(익은 토마토의 주변)에 안익은 토마토가 있으면
                if(map[newX][newY] == 0) {
                    // 토마토 익음
                    map[newX][newY] = 1;
                    // depth 증가
                    visited[newX][newY] = visited[node.getX()][node.getY()] + 1;
                    // 익은 새로운 토마토의 위치를 넣는다.
                    queue.add(new Node(newX, newY));
                }
            }
        }

        // 토마토가 모두 익지 못하면 -1 출력
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                max = Math.max(visited[i][j], max);
            }
        }
        System.out.print(max);
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
