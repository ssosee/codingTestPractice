package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz1012 {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 갯수
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 배추밭 가로
            m = Integer.parseInt(st.nextToken());
            // 배추밭 세로
            n = Integer.parseInt(st.nextToken());
            // 배추 위치 갯수
            int k = Integer.parseInt(st.nextToken());

            // 배추 밭
            int[][] filed = new int[m][n];
            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // 배추 심기
                filed[x][y] = 1;
            }

            // 배추밭 방문 이력
            boolean[][] visited = new boolean[m][n];
            int count = 0; // 지렁이 갯수
            for(int x = 0; x < m; x++) {
                for(int y = 0; y < n; y++) {
                    if(!visited[x][y] && filed[x][y] == 1) {
                        bfs(filed, visited, x, y);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void bfs(int[][] filed, boolean[][] visited, int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int[] upAndDown = {1, -1, 0, 0};
            int[] leftAndRight = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++) {
                // 탐색해야하는 배추밭 위치
                int newX = node.getX() + upAndDown[i];
                int newY = node.getY() + leftAndRight[i];

                // 범위를 벗어난 경우
                if(newX < 0 || newY < 0 || newX >= m || newY >= n) {
                    continue;
                }

                // 탐색해야하는 곳에 방문 이력이 없고, 배추밭에 배추가 있으면 bfs 실행
                if(!visited[newX][newY] && filed[newX][newY] == 1) {
                    visited[newX][newY] = true;
                    queue.add(new Node(newX, newY));
                }
            }
        }
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
