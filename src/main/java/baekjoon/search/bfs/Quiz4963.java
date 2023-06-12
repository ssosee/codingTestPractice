package baekjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz4963 {

    static int w = 0;
    static int h = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) {
                break;
            }

            // 1은 땅, 0은 바다
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[][] visited = new boolean[h][w];
            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        bfs(map, visited, i, j);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int[][] map, boolean[][] visited, int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int[] upDown =     {1, -1, 0, 0, 1, 1, -1, -1};
            int[] leftRight =  {0, 0, 1, -1, -1, 1, 1, -1};

            for(int i = 0; i < 8; i++) {
                int newX = node.getX() + upDown[i];
                int newY = node.getY() + leftRight[i];

                if(newX < 0 || newY < 0 || newX >= h || newY >= w) {
                    continue;
                }

                // 탐색한 적이 없고, 땅이라면
                if(!visited[newX][newY] && map[newX][newY] == 1) {
                    queue.add(new Node(newX, newY));
                    visited[newX][newY] = true;
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
