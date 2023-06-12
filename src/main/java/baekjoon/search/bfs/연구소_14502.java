package baekjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_14502 {

    static int n;
    static int m;
    static int[][] map;
    static int[] ud = {1, -1, 0 , 0};
    static int[] lr = {0, 0, 1, -1};
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
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

        /**
         * 1. 벽 3개 세우기
         * 2. 벽 3개를 세우면 바이러스를 퍼뜨린다.
         * 3. 1-2를 반복하고 가장 넒은 안전영역을 출력
         */
        dfs(0);

        System.out.print(max);
        br.close();
    }

    private static void dfs(int wall) {

        if(wall == 3) {
            // 바이러스 퍼뜨리기
            max = Math.max(bfs(), max);
            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1; // 벽세우기
                    dfs(wall + 1);
                    map[i][j] = 0; // 벽 초기화
                }
            }
        }
    }

    // 바이러스 퍼뜨리기
    private static int bfs() {
        Queue<Lab> queue = new ArrayDeque<>();
        visited = new boolean[n][m];

        // 바이러스 위치 큐에 저장
        int[][] dumyLab = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dumyLab[i][j] = map[i][j];
                if(dumyLab[i][j] == 2) {
                    queue.offer(new Lab(i, j));
                    // visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Lab poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            for(int i = 0; i < 4; i++) {
                int newX = x + lr[i];
                int newY = y + ud[i];

                if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

                if(!visited[newX][newY] && dumyLab[newX][newY] == 0) {
                    dumyLab[newX][newY] = 2;
                    queue.add(new Lab(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dumyLab[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    static class Lab {
        private int x;
        private int y;

        public Lab(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
