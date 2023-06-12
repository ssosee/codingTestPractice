package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz11399 {
    public static void main(String[] args) throws IOException {
        /**
         * 걸리는 시간: [3, 1, 4, 3, 2]
         * 3+(1+3)+(4+1+3)+(3+4+1+3)+(2+1+3+4+3) -> 39
         *
         * 걸리는 시간: [1, 2, 3, 3, 4]
         * 1 + 1+2 + 1+2+3 + 1+2+3+3 + 1+2+3+3+4
         * 1   4      10       23
         *
         * 오름차순 정렬하여 재귀로 합을 구하면 최소시간
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] time = new int[n];
        for(int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(time);

        int result = 0;
        int sum = 0;
        for(int i = 0; i < time.length; i++) {
            sum += time[i];
            result += sum;
        }

        System.out.print(result);
        br.close();
    }
}
