package backjoon.datastructure.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11660 {
    public static void main(String[] args) throws IOException {

        // 1. n, m 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());	// 2차원 배열의 크기
        int m = Integer.parseInt(st.nextToken());	// 구해야하는 구간 합의 수

        // 2. 2차원 합 배열 구하기
        int[][] sumArr = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                sumArr[i][j] = sumArr[i-1][j] + sumArr[i][j-1] + Integer.parseInt(st.nextToken()) - sumArr[i-1][j-1];
            }
        }

        // 3. 구간 합 계산
        for(int i = 0; i < m; i++) {
            /**
             * (i, j) 구간 합
             * S[j] - S[i-1]
             *
             *
             * (x1, y1) (x2, y2) 까지 합
             * S[x2][y2] - S[x1-1][y2] - S[x2][y1-1] + S[x1-1][y1-1]
             */
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = sumArr[x2][y2] - sumArr[x1-1][y2] - sumArr[x2][y1-1] + sumArr[x1-1][y1-1];
            System.out.println(result);
        }
    }
}
