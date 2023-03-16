package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1012 {

    static int weight = 0;
    static int height = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 갯수
        int testNum = Integer.parseInt(br.readLine());
        for(int t = 0; t < testNum; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 배추 밭의 열 갯수
            weight = Integer.parseInt(st.nextToken());
            // 베추 밭의 행 갯수
            height = Integer.parseInt(st.nextToken());

            // 배추 위치 갯수
            int nodNum = Integer.parseInt(st.nextToken());

            // 배추 밭
            int[][] arr = new int[weight][height];
            for(int i = 0; i < nodNum; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
            }

            // 방문 이력
            boolean[][] visited = new boolean[weight][height];
            int count = 0;
            for(int i = 0; i < weight; i++) {
                for(int j = 0; j < height; j++) {
                    // 방문 이력이 없고, 배추가 있다면 탐색
                    if(!visited[i][j] && arr[i][j] == 1) {
                        dfs(arr, visited, i, j);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int[][] arr, boolean[][] visited, int startX, int startY) {
        // 상, 하, 좌, 우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        visited[startX][startY] = true;

        // 4가지 방향으로 탐색
        for(int i = 0; i < 4; i++) {
            int x = startX + dx[i];
            int y = startY + dy[i];

            // 배추 밭을 벗어나는 경우
            if(x < 0 || x >= weight || y < 0 || y >= height) {
                // 아래 코드를 진행하지 않고 다음 반복을 진행
                continue;
            }

            // 이동한 곳에 배추가 있고, 방문 이력이 없다면 dfs실행
            if(arr[x][y] == 1 && !visited[x][y]) {
                dfs(arr, visited, x, y);
            }
        }
    }
}
