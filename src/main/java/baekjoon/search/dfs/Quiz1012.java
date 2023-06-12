package baekjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1012 {

    static int width = 0;
    static int height = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 갯수
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 배추 밭의 가로 길이(-)
            width = Integer.parseInt(st.nextToken());
            // 배추 밭의 세로 길이(|)
            height = Integer.parseInt(st.nextToken());
            // 배추 위치 갯수
            int positionNum = Integer.parseInt(st.nextToken());

            // 배추 밭 배열
            int[][] field = new int[width][height];
            for(int k = 0; k < positionNum; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                field[x][y] = 1;
            }

            // 배추 밭 탐색 방문 이력
            boolean[][] visited = new boolean[width][height];
            // 지렁이 갯수(탐색을 마치면 지렁이 1 증가)
            int count = 0;
            for(int x = 0; x < width; x++) {
                for(int y = 0; y < height; y++) {
                    // 배추밭에 배추가 있고, 방문 이력이 없으면 탐색(bfs)
                    if(field[x][y] == 1 && !visited[x][y]) {
                        dfs(field, visited, x, y);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int[][] field, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        // 배추가 있는 곳을 중심으로 상, 하, 좌, 우 탐색 해야함
        // 상, 하, 좌, 우 1칸씩만 이동이 가능함
        int[] upAndDown = {1, -1, 0, 0};
        int[] leftAndRight = {0, 0, 1, -1};
        for(int i = 0; i < 4; i++) {
            // 탐색 위치
            int newX = x + upAndDown[i]; // 기존 위치 + 이동 위치 = 새로운 위치
            int newY = y + leftAndRight[i]; // 기존 위치 + 이동 위치 = 새로운 위치

            // 배추 밭 범위를 벗어난 경우
            // 배추 밭은 0부터 시작하기 때문에 새로운 위치가 width 또는 height랑 같으면 안됨
            if(newX < 0 || newX >= width || newY < 0 || newY >= height) {
                continue;
            }

            // 새로운 위치에 배추가 있고, 탐색 이력이 없다면 탐색 한다.
            if(field[newX][newY] == 1 && !visited[newX][newY]) {
                dfs(field, visited, newX, newY);
            }
        }
    }
}
