package baekjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2468 {
    static int n;
    static int[][] map;
    static boolean[][] visited;

    // 이 녀석을 static으로 하지 않으면
    // dfs를 수행할때 마다 신규로 메모리를 할당한다.
    static int[] ud = {1, -1, 0, 0};
    static int[] lr = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 인접 행렬 생성
        int max = Integer.MIN_VALUE;
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 지역의 최대 높이
                max = Math.max(max, map[i][j]);
            }
        }


        // 안전지역 카운트
        int count = 0;
        int maxCount = Integer.MIN_VALUE;
        // 지역의 최대 높이 만큼 dfs 수행
        for(int k = 0; k <= max; k++) {
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && map[i][j] > k) {
                        dfs(i, j, k);
                        // 안전지역 카운트 증가
                        count++;
                    }
                }
            }
            // 안전영역 최대 갯수 계산
            maxCount = Math.max(count, maxCount);
            // 안전지역 카운트 초기화
            count = 0;
        }

        System.out.print(maxCount);
        br.close();
    }

    private static void dfs(int x, int y, int k) {
        for(int i = 0; i < 4; i++) {
            // 상, 하, 좌, 우 이동
            int newX = x + ud[i];
            int newY = y + lr[i];

            // 범위 체크
            if(newX < 0 || newY < 0 || newX >= n || newY >= n) {
                continue;
            }

            if(!visited[newX][newY] && map[newX][newY] > k) {
                visited[newX][newY] = true;
                dfs(newX, newY, k);
            }
        }
    }
}
