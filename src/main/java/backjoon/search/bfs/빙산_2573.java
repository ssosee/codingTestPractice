package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산_2573 {

    static int n;
    static int m;
    static int[] ud = {1, -1, 0, 0};
    static int[] rl = {0, 0, 1, -1};
    static boolean[][] visited;
    static boolean[][] isIce;
    static int[][] map;
    static int cnt = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        // 일년 후, 바다에 인접한 칸수만큼 빙산이 녹는다.
        // 빙산이 두 덩어리 이상이 될때 까지의 년수
        // 두 덩어리 이상으로 분리되지 않으면 0 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        // 빙산이 두 덩이 이상이면 탈출
        while (cnt < 2) {

            if(cnt == 0) {
                year = 0;
                break;
            }

            isIce = new boolean[n][m];
            bfs(); // 1년 후 빙산
            // 빙산 갯수 계산
            cnt = getIceBerg();

            year++;
        }

        System.out.print(year);
        br.close();
    }

    // 빙산 뭉탱이 계산
    // DFS를 사용한 이유는 뭉탱이 계산 속도가 빨라서
    private static int getIceBerg() {
        int cnt = 0;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 방문 이력이 없고, 빙산이 있으면
                if (!visited[i][j] && map[i][j] > 0) {
                    dfs(i, j); // 탐색이 끝난것은 뭉탱이 하나가 나온것을 의미
                    cnt++; // 뭉탱이 증가
                }
            }
        }

        return cnt;
    }

    // 빙산이 몇 덩이 인지 계산
    private static void dfs(int startX, int startY) {
        for(int i = 0; i < 4; i++) {
            int newX = startX + ud[i];
            int newY = startY + rl[i];

            if(!visited[newX][newY] && map[newX][newY] > 0) {
                visited[newX][newY] = true;
                dfs(newX, newY);
            }
        }
    }

    // 1년 후 빙산 계산
    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();

        int count = 0;

        // 미리 빙산을 큐에 담는다.
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 빙산만 담는다.
                if(map[i][j] > 0) {
                    queue.add(new Node(i, j));
                    // 방문이력을 남긴다.
                    // 빙산이라는 것을 기록한다.
                    // 빙산이라는 것을 기록하지 않으면
                    // 빙산이 녹아 바다가 되면, 그냥 바다인것으로 생각하여 계산한다.
                    isIce[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            // 빙산 주변 바다 체크
            for(int i = 0; i < 4; i++) {
                int newX = x + rl[i];
                int newY = y + ud[i];

                // 범위 체크
                if(newX < 0 || newY < 0 || newX >= n || newY >= m) {
                    continue;
                }

                // 빙산 체크
                // 애초에 빙산 아니고, 빙산이 없으면
                if(!isIce[newX][newY] && map[newX][newY] <= 0) {
                    count++;
                }
            }

            // 빙산을 녹인다.
            if(map[x][y] > 0) {
                map[x][y] -= count;
                count = 0;
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
    }
}
