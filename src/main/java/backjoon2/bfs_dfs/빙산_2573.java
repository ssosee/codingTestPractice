package backjoon2.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산_2573 {

    static int n;
    static int m;
    static int[][] map;
    static int[] lr = {1, -1, 0, 0};
    static int[] ud = {0, 0, 1, -1};
    static boolean[][] isIce;
    static boolean[][] visited;
    static int cnt = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 지도 정보
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (cnt < 2) {

            if(cnt == 0) {
                result = 0;
                break;
            }

            // 1년 후 빙산
            bfs();

            // 빙산 덩어리 확인
            visited = new boolean[n][m];
            cnt = checkIceCount();

            result++;
        }

        System.out.print(result);
        br.close();
    }

    // 빙산 덩어리 체크
    private static int checkIceCount() {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && map[i][j] > 0) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    // 빙산 덩어리 체크
    private static void dfs(int startX, int startY) {
       for(int i = 0 ; i < 4; i++) {
           int newX = startX + lr[i];
           int newY = startY + ud[i];

           if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

           if(!visited[newX][newY] && map[newX][newY] > 0) {
               visited[newX][newY] = true;
               dfs(newX, newY);
           }
       }
    }

    // 1년 후 빙산
    private static void bfs() {

        Queue<IceMap> queue = new ArrayDeque<>();
        isIce = new boolean[n][m];

        // 빙산만 큐에 넣는다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] > 0 && !isIce[i][j]) {
                    queue.offer(new IceMap(i, j));
                    isIce[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {

            IceMap poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            int seaCount = 0;
            // 바다 체크
            for (int i = 0; i < 4; i++) {
                int newX = lr[i] + x;
                int newY = ud[i] + y;

                if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

                // 애초에 바다였고, 빙산이 아니면
                if(!isIce[newX][newY] && map[newX][newY] <= 0) {
                    seaCount++;
                }
            }

            // 빙산이면
            if(map[x][y] > 0) {
                // 바다 갯수 만큼 빙산 녹이기
                map[x][y] -= seaCount;
            }
        }
    }

    static class IceMap {
        private int x;
        private int y;

        public IceMap(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
