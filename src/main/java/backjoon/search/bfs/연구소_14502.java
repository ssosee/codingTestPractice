package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_14502 {

    static int n;
    static int m;
    static boolean[][] visited;
    static boolean[][] is0;
    static int[][] map;
    static int[] ud = {1, -1, 0, 0};
    static int[] lr = {0, 0, 1, -1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        // 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
        // 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다
        // 안전 영역의 크기를 출력
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

        // 1. 0인 곳에 벽 3개를 세운다.
        // 2. 바이러스를 퍼뜨린다.
        // 3. 안전영역을 구한다.
        simulate(0);

        System.out.print(max);
        br.close();
    }

    private static void simulate(int wallCount) {

        // 벽이 3개 이면
        if(wallCount == 3) {
            // 바이러스를 퍼뜨려본다.
            max = Math.max(max, bfs());
            return;
        }

        // 벽세우기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 빈 공간이면
                if(map[i][j] == 0) {
                    map[i][j] = 1; // 벽 세우기
                    simulate(wallCount + 1);
                    map[i][j] = 0; // 초기화
                }
            }
        }
    }

    // 바이러스 퍼뜨리기
    private static int bfs() {
        Queue<Lab> queue = new ArrayDeque<>();
        visited = new boolean[n][m];

        // 바이러스 시작위치를 큐에 저장
        int[][] cloneMap = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 2차원 배열을 깊은 복사하려면 for문 활용해야함
                cloneMap[i][j] = map[i][j];
                if(cloneMap[i][j] == 2) {
                    queue.offer(new Lab(i, j));
                    visited[i][j] = true;
                }
            }
        }

        // 빈공간에 바이러스 퍼뜨리기
        while (!queue.isEmpty()) {
            Lab poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            for(int i = 0; i < 4; i++) {
                int newX = x + lr[i];
                int newY = y + ud[i];

                // 범위 체크
                if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

                // 방문한 이력이 없고, 빈공간이면 바이러스 전파
                if(!visited[newX][newY] && cloneMap[newX][newY] == 0) {
                    cloneMap[newX][newY] = 2;
                    queue.offer(new Lab(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }

        // 안전 구역 게산
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(cloneMap[i][j] == 0) {
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
