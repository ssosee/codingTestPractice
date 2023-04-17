package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz14503_2 {
    static int n;
    static int m;
    static int startX;
    static int startY;
    static int front;
    static int[][] map;
    // 북(0), 남(2), 서(1), 동(4)
    static int[] ns = {-1, 0, 1, 0};
    static int[] we = {0, 1, 0, -1};
    // 청소한 블록 갯수
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        // 0: 북, 1: 동, 2: 남, 3: 서
        front = Integer.parseInt(st.nextToken());

        // 지도 저장
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs 수행
        dfs(startX, startY, front);

        System.out.print(count);
        br.close();
    }

    private static void dfs(int x, int y, int direction) {

        // 현재 칸이 청소 X
        if(map[x][y] == 0) {
            // 청소
            map[x][y] = -1;
            count++;
        }

        // 주위(4칸)가 다 깨끗한지
        if(isClean(x, y)) {
            // 후진
            int newDirection = (direction + 2) % 4;
            int newX = x + ns[newDirection];
            int newY = y + we[newDirection];

            // 범위 체크
            if(newX < 0 || newY < 0 || newX >= n || newY >= m) return;

            // 후진 불가
            if(map[newX][newY] == 1) return;

            // 방향은 유지해야함
            dfs(newX, newY, direction);
        }
        // 주위(4칸)에 더러운 칸이 있는 경우
        else {
            // 최대 4번 회전 가능
            for(int i = 0; i < 4; i++) {
                // 회전
                direction = (direction + 3) % 4;
                int newX = x + ns[direction];
                int newY = y + we[direction];

                if (newX < 0 || newY < 0 || newX >= n || newY >= m) return;

                // dfs(newX, newY, direction);
                // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한 칸 전진
                if (map[newX][newY] == 0) {
                    dfs(newX, newY, direction);
                    break;
                }
            }
        }
    }

    // 주위(4칸)가 다 깨끗한지
    private static boolean isClean(int x, int y) {
        for(int i = 0; i < 4; i++) {
            int newX = x + ns[i];
            int newY = y + we[i];

            if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

            // 더러운 곳이 1곳이라도 있으면
            if(map[newX][newY] == 0) {
                return false;
            }
        }

        return true;
    }
}
