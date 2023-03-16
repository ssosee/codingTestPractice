package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz2667 {
    static int n = 0;
    static int houseCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 지도의 크기(정사각형임)
        n = Integer.parseInt(br.readLine());

        // 지도 배열
        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++) {
            // 한줄로 읽어와서
            String str = br.readLine();
            // char로 변환
            char[] chars = str.toCharArray();
            for(int j = 0; j < n; j++) {
                // 아스키코드로 값을 읽어옴
                int data = chars[j] - '0';
                map[i][j] = data;
            }
        }



        // 탐색 이력 배열
        boolean[][] visited = new boolean[n][n];
        // 총단지 수
        int townNum = 0;
        // 집 수
        int houseNum = 1;
        // 각 단지의 집 수를 저장하는 배열
        List<Integer> houseNumList = new ArrayList<>();
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                // 탐색 이력이 없고, 지도에 집이 있으면 탐색을 한다.
                if(!visited[x][y] && map[x][y] == 1) {
                    dfs(map, visited, x, y);
                    townNum++;
                    houseNumList.add(houseCount);
                    houseCount = 0;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(townNum).append("\n");
        // 오름차순 정렬
        houseNumList.sort(Comparator.naturalOrder());
        for(Integer i : houseNumList) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    private static void dfs(int[][] map, boolean[][] visited, int x, int y) {
        houseCount++; // 아파트 수 증가
        visited[x][y] = true;
        int[] upAndDown = {1, -1, 0, 0};
        int[] leftAndRight = {0, 0, 1, -1};
        for(int i = 0; i < 4; i++) {
            int newX = x + upAndDown[i]; // 탐색할 새로운 위치
            int newY = y + leftAndRight[i]; // 탐색할 새로운 위치

            // 탐색 위치를 벗어나는 경우
            if(newX < 0 || newY < 0 || newX >= n || newY >= n) {
                continue;
            }

            // 새로운 탐색 위치에 방문한 적이 없고, 집이 있으면 탐색을 한다.
            if(!visited[newX][newY] && map[newX][newY] == 1) {
                dfs(map, visited, newX, newY);
            }
        }
    }
}
