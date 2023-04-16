package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1987 {

    static int r;
    static int c;
    // 상, 하, 좌, 우
    static int[] ud = {1, -1, 0, 0};
    static int[] lr = {0, 0, 1, -1};
    static Character[][] map;
    // 알파벳은 총 26개
    static boolean[] path = new boolean[26];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 같은 알파벳을 지날 수 없다.
        // 시작점은 0, 0에서 최대 몇 칸을 움직 일 수 있는지 구하시오.

        // 알파벳 저장
        map = new Character[r][c];
        for(int i = 0; i < r; i++) {
            String str = br.readLine();
            char[] chars = str.toCharArray();
            for(int j = 0; j < c; j++) {
                map[i][j] = chars[j];
            }
        }

        // 시작 위치
        path[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        // 출력
        System.out.print(max);
        br.close();
    }

    private static void dfs(int x, int y, int depth) {

        // 깊이가 최대 계산
        max = Math.max(depth, max);

        for(int i = 0; i < 4; i++) {
            // 상, 하, 좌, 우 이동
            int newX = x + ud[i];
            int newY = y + lr[i];

            // 범위 체크
            if(newX < 0 || newY < 0 || newX >= r || newY >= c) {
                continue;
            }

            // 방문 이력없고 지나온 알파벳이 없으면
            if(!path[map[newX][newY] - 'A']) {
                // 알파벳 사용
                path[map[newX][newY] - 'A'] = true;
                // dfs 수행
                dfs(newX, newY, depth + 1);
                // 알파벳 초기화
                path[map[newX][newY] - 'A'] = false;
            }
        }
    }
}
