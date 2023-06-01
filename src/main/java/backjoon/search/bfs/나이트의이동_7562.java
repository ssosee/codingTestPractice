package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나이트의이동_7562 {

    static int n;
    static int[] moveX = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] moveY = {1, -1, 1, -1, 2, -2, 2, -2};
    static int[][] map;
    static boolean[][] visited;
    static int goalX;
    static int goalY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 현재 위치
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            // 목표지점
            st = new StringTokenizer(br.readLine());
            goalX = Integer.parseInt(st.nextToken());
            goalY = Integer.parseInt(st.nextToken());

            int bfs = bfs(startX, startY);
            sb.append(bfs).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    private static int bfs(int startX, int startY) {
        visited = new boolean[n][n];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int count = poll.count;

            if(x == goalX && y == goalY) {
                return count;
            }

            for(int i = 0; i < 8; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                if(newX < 0 || newY < 0 || newX >= n || newY >= n) continue;

                if(!visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new Node(newX, newY, count + 1));
                }
            }
        }
        return 0;
    }

    static class Node {
        private int x;
        private int y;
        private int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
