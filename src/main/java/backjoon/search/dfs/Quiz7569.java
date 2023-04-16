package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz7569 {

    static int m;
    static int n;
    static int h;

    static int[][][] box;
    static boolean[][][] visited;

    // x축
    static int[] ud = {1, -1, 0, 0, 0, 0};
    // y축
    static int[] lr = {0, 0, 1, -1, 0, 0};
    // z축
    static int[] zud = {0, 0, 0, 0, 1, -1};

    static Queue<Node> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // 모든 토마토가 익었다고 가정
        boolean flag = true;
        // 토마토 박스
        box = new int[h][n][m];
        // 토마토 저장
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    box[i][j][k] = tomato;
                    // 익지 않은 토마토가 1개라도 있으면
                    if(tomato == 0) {
                        // 모든 토마토가 익은 상태가 아님
                        flag = false;
                    }
                }
            }
        }

        // 처음부터 모든 토마토가 익은 상태
        if(flag) {
            System.out.print(0);
            br.close();
            return;
        }

        // 1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토가 없음
        visited = new boolean[h][n][m];
        // bfs 실행
        int bfs = bfs();

        System.out.print(bfs);
        br.close();
    }

    private static int bfs() {

        // 초기 토마토 큐에 삽입
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(box[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        queue.offer(new Node(j, k, i, 0));
                    }
                }
            }
        }

        // 날짜 결과
        int result = 0;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int x = poll.getX();
            int y = poll.getY();
            int z = poll.getZ();
            int days = poll.getDays();

            for(int i = 0; i < 6; i++) {
                // 인접 토마토 위치
                int newX = x + ud[i];
                int newY = y + lr[i];
                int newZ = z + zud[i];

                // 범위 체크
                if (newX >= n || newY >= m || newZ >= h || newX < 0 || newY < 0 || newZ < 0) continue;

                // 빈 토마토면 건너뛴다.
                if(box[newZ][newX][newY] == -1) continue;

                // 토마토 인접 위치에 방문한 적이 없고, 익은 토마토가 있으면
                if (!visited[newZ][newX][newY] && box[z][x][y] == 1) {
                    visited[newZ][newX][newY] = true;

                    // 익지 않은 토마토가 있으면
                    if(box[newZ][newX][newY] == 0) {
                        // 근처 토마토가 익는다.
                        box[newZ][newX][newY] = 1;
                        // 근처 토마토를 큐에 삽입
                        queue.add(new Node(newX, newY, newZ, days + 1));
                        // 날짜 저장
                        result = days + 1;
                    }
                }
            }
        }

        // 모두 익지 못하면
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(box[i][j][k] == 0) {
                        return -1;
                    }
                }
            }
        }

        return result;
    }

    static class Node {
        private int x;
        private int y;
        private int z;
        private int days;

        public Node(int x, int y, int z, int days) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.days = days;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public int getDays() {
            return days;
        }
    }
}
