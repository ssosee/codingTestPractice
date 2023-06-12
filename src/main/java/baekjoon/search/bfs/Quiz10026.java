package baekjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Quiz10026 {

    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 사진의 크기
        n = Integer.parseInt(br.readLine());

        // 사진
        char[][] photo1 = new char[n][n];
        char[][] photo2 = new char[n][n];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            char[] chars = str.toCharArray();
            for(int j = 0; j < chars.length; j++) {
                photo1[i][j] = chars[j];
                photo2[i][j] = chars[j];
                if (chars[j] == 'R') {
                    photo2[i][j] = 'G';
                }
            }
        }

        boolean[][] visited = new boolean[n][n];
        int count1 = 0;
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                if(!visited[x][y]) {
                    bfs(visited, photo1, x, y);
                    count1++;
                }
            }
        }

        visited = new boolean[n][n];
        int count2 = 0;
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                if(!visited[x][y]) {
                    bfs(visited, photo2, x, y);
                    count2++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count1).append(" ").append(count2);
        System.out.print(sb);
    }

    private static void bfs(boolean[][] visited, char[][] photo, int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int[] upAndDown = {1, -1, 0, 0};
            int[] leftAndDown = {0, 0, 1, -1};
            char color = photo[node.getX()][node.getY()];

            for(int i = 0; i < 4; i++) {
                int newX = upAndDown[i] + node.getX();
                int newY = leftAndDown[i] + node.getY();

                // 범위를 벗어난 경우
                if(newX < 0 || newY < 0 || newX >= n || newY >= n) {
                    continue;
                }

                // 방문 기록이 없고 탐색 위치에 색이 같으면
                if(!visited[newX][newY] && photo[newX][newY] == color) {
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
