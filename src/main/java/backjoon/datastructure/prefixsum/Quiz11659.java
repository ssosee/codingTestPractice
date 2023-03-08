package backjoon.datastructure.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        // 배열 갯수
        int n = Integer.parseInt(st1.nextToken());
        // 질의 횟수
        int m = Integer.parseInt(st1.nextToken());

        // 합 배열 계산
        int[] rangeSum = getRangeSum(br, n);

        // 구간 저장
        int[][] range = new int[m][2];
        for(int i = 0; i < m; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                range[i][j] = Integer.parseInt(st3.nextToken());
            }
        }

        // 결과
        for(int k = 0; k < m; k++) {
            System.out.println(getResult(range, rangeSum, k));
        }
    }

    // 합 배열 계산
    private static int[] getRangeSum(BufferedReader br, int n) throws IOException {
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] rangeSum = new int[n+1];
        for(int i = 0; i < n; i++) {
            rangeSum[i+1] = rangeSum[i] + Integer.parseInt(st2.nextToken());
        }
        return rangeSum;
    }

    // 구간 합 결과
    private static int getResult(int[][] range, int[] rangeSum, int k) {
        /**
         * 배열의 i ~ j 까지 구간 합
         * S[j] - S[i-1]
         */
        int i = range[k][0];
        int j = range[k][1];

        return rangeSum[j] - rangeSum[i-1];
    }
}
