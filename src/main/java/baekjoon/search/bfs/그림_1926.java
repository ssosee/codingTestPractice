package baekjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그림_1926 {

    public static int[] ud = {1, -1, 0, 0};
    public static int[] lr = {0, 0, 1, -1};
    public static int n;
    public static int m;
    public static int[][] picture;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        picture = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                picture[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 그림은 1로 연결된 것이 그림
        // 그림의 갯수, 가장 넒은 그림의 넓이
        // 그림이 없으면 0
        int count = 0;
        int max = 0; // 그림이 없으면 0
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(picture[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(bfs(i, j), max);
                    count++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        sb.append(max);

        System.out.print(sb);
        br.close();
    }

    public static int bfs(int startX, int startY) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(startX, startY));
        visited[startX][startY] = true;
        int value = 1;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            for(int i = 0; i < 4; i++) {
                int newX = poll.x + ud[i];
                int newY = poll.y + lr[i];

                // 범위 체크
                if(newX < 0 || newY < 0 || newX >= n || newY >= m) {
                    continue;
                }

                // 방문한 이력이 없고, 그림이면 탐색
                if(!visited[newX][newY] && picture[newX][newY] == 1) {
                    queue.add(new Node(newX, newY));
                    visited[newX][newY] = true;
                    value++;
                }
            }
        }

        return value;
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
