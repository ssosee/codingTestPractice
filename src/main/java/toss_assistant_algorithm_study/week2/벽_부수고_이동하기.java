package toss_assistant_algorithm_study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 벽_부수고_이동하기 {
    /**
     * 1. 아이디어
     *  bfs를 이용하여 [x][y]벽을 1개씩 부셨을 때의 최단거리를 모두 계산한다.
     *  vistied는 3차원 배열을 사용한다.
     *      벽을 안부수고 탐색 [x][y][0],
     *      벽을 부수고 탐색 [x][y][1]
     *
     *  벽을 부신적이 없으면 벽을 부신다.
     *  벽을 부신적이 있으면 벽을 못 부신다.
     *
     * 2. 시간복잡도
     *  O(M * N) -> O(1000 * 1000)
     *
     * 3. 자료구조
     * Queue    -> new ArrayDeque<>()
     * 3차원 배열 -> static boolean[][][] visited
     */

    static int n;
    static int m;
    static int[] moveX = {1, -1, 0 ,0};
    static int[] moveY = {0, 0, 1 ,-1};
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j - 1) - '0';
            }
        }

        int bfs = bfs();
        System.out.print(bfs);

        br.close();
    }

    private static int bfs() {
        visited = new boolean[n+1][m+1][2];
        visited[1][1][0] = true;
        visited[1][1][1] = true;

        Queue<Path> queue = new ArrayDeque<>();
        queue.offer(new Path(1, 1, 1, false));

        while (!queue.isEmpty()) {
            Path poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int count = poll.count;
            boolean isDestroyWall = poll.isDestroyWall;

            if(x == n && y == m) return count;

            for(int i = 0; i < 4; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                // 범위 체크
                if(newX < 1 || newY < 1 || newX > n || newY > m) continue;

                // 벽이 아니면
                if(map[newX][newY] == 0) {
                    // 이미 벽을 부순 경우
                    if(isDestroyWall && !visited[newX][newY][1]) {
                        visited[newX][newY][1] = true;
                        queue.offer(new Path(newX, newY, count + 1, true));
                    }
                    // 벽을 부순적이 없는 경우
                    else if(!isDestroyWall && !visited[newX][newY][0]) {
                        visited[newX][newY][0] = true;
                        queue.offer(new Path(newX, newY, count + 1, false));
                    }
                }
                //벽이고 벽을 부순적이 없는 경우
                else if(!isDestroyWall) {
                    queue.offer(new Path(newX, newY, count + 1, true));
                }
            }
        }

        return -1;
    }

    static class Path {
        private int x;
        private int y;
        private int count;
        private boolean isDestroyWall;

        public Path(int x, int y, int count, boolean isDestroyWall) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.isDestroyWall = isDestroyWall;
        }
    }
}
