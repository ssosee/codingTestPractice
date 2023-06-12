package baekjoon.search.bfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz2178 {

    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 미로 입력
        int[][] miro = new int[n][m];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            char[] chars = str.toCharArray();
            for(int j = 0; j < chars.length; j++) {
                // 아스키코드를 정수형으로 변환
                int data = chars[j] - '0';
                miro[i][j] = data;
            }
        }

        // 방문 이력 배열
        boolean[][] visited = new boolean[n][m];
        bfs(miro, visited, 0, 0);
        System.out.print(miro[n-1][m-1]);
    }

    private static void bfs(int[][] miro, boolean[][] visited, int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;
        int[] upAndDown = {1, -1, 0, 0};
        int[] leftAndRight = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            // 노드를 하나 꺼낸다.
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = node.getX() + upAndDown[i];
                int newY = node.getY() + leftAndRight[i];

                if (newX < 0 || newY < 0 || newX >= n || newY >= m) {
                    continue;
                }

                // 새로운 x,y에 방문 이력이 없고, 미로에서 갈 수 있는 길이면 탐색한다.
                if (!visited[newX][newY] && miro[newX][newY] == 1) {
                    visited[newX][newY] = true;
                    queue.add(new Node(newX, newY));
                    // 새로운 좌표에 기존 좌표의 미로에 현재 탐색 깊이를 기록
                    miro[newX][newY] = miro[node.getX()][node.getY()] + 1;
                }
            }
        }
    }

    // 노드 클래스
    private static class Node {
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
