package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz10026 {

    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 일반 사람이 보이는 사진
        char[][] photo1 = new char[n][n];
        // 색약자 눈에 보이는 사진
        char[][] photo2 = new char[n][n];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            char[] chars = str.toCharArray();
            for(int j = 0; j < chars.length; j++) {
                photo1[i][j] = chars[j];
                if(chars[j] == 'R') {
                    photo2[i][j] = 'G';
                } else {
                    photo2[i][j] = chars[j];
                }
            }
        }

        boolean[][] visited = new boolean[n][n];
        int count1 = 0;
        int count2 = 0;
        // 색약이 아닌 경우
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    dfs(visited, photo1, i, j);
                    count1++;
                }
            }
        }

        visited = new boolean[n][n];
        // 색약인 경우
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    dfs(visited, photo2, i, j);
                    count2++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count1).append(" ").append(count2);
        System.out.print(sb);
    }

    private static void dfs(boolean[][] visited, char[][] photo, int x, int y) {
        visited[x][y] = true;
        int[] upAndDown = {1, -1, 0, 0};
        int[] leftAndRight = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            // 탐색(새로운) 위치
            int newX = upAndDown[i] + x;
            int newY = leftAndRight[i] + y;

            // 탐색(새로운) 위치를 벗어나는 경우
            if (newX < 0 || newY < 0 || newX >= n || newY >= n) {
                continue;
            }

            // 탐색(새로운) 위치에 방문한적이 없고, 탐색(새로운) 위치의 색과 기존 위치의 색이 동일하면 DFS 실행
            if (!visited[newX][newY] && photo[newX][newY] == photo[x][y]) {
                dfs(visited, photo, newX, newY);
            }
        }
    }
}
